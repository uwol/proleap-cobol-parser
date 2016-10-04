/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package org.cobol85.runner.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.Trees;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.cobol85.Cobol85Lexer;
import org.cobol85.Cobol85Parser;
import org.cobol85.Cobol85Parser.StartRuleContext;
import org.cobol85.ThrowingErrorListener;
import org.cobol85.applicationcontext.Cobol85GrammarContext;
import org.cobol85.preprocessor.Cobol85Preprocessor.Cobol85SourceFormatEnum;
import org.cobol85.runner.Cobol85ParseTestRunner;

/**
 * Cobol 85 parse runner for JUnit tests.
 */
public class Cobol85ParseTestRunnerImpl implements Cobol85ParseTestRunner {

	private final static String[] cobolFileExtensions = new String[] { "cbl", "cob", "jcl", "txt", "" };

	private final static Logger LOG = LogManager.getLogger(Cobol85ParseTestRunnerImpl.class);

	public final static String TREE_SUFFIX = ".tree";

	protected static boolean isCobolFile(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return inputFile.isFile() && Arrays.asList(cobolFileExtensions).contains(extension);
	}

	protected void doCompareParseTree(final File treeFile, final StartRuleContext startRule, final Cobol85Parser parser)
			throws IOException {
		final String treeFileData = FileUtils.readFileToString(treeFile);

		if (!Strings.isBlank(treeFileData)) {
			LOG.info("Comparing parse tree with file {}.", treeFile.getName());

			final String inputFileTree = Trees.toStringTree(startRule, parser);
			final String cleanedInputFileTree = org.cobol85.util.StringUtils.cleanFileTree(inputFileTree);
			final String cleanedTreeFileData = org.cobol85.util.StringUtils.cleanFileTree(treeFileData);

			assertEquals(cleanedTreeFileData, cleanedInputFileTree);
		} else {
			LOG.info("Ignoring empty parse tree file {}.", treeFile.getName());
		}
	}

	protected void doParse(final String preProcessedInput, final File inputFile) throws IOException {
		final Cobol85Lexer lexer = new Cobol85Lexer(new ANTLRInputStream(preProcessedInput));

		lexer.removeErrorListeners();
		lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final Cobol85Parser parser = new Cobol85Parser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(ThrowingErrorListener.INSTANCE);

		final StartRuleContext startRule = parser.startRule();
		final File treeFile = new File(inputFile.getAbsolutePath() + TREE_SUFFIX);

		if (treeFile.exists()) {
			doCompareParseTree(treeFile, startRule, parser);
		}
	}

	@Override
	public void parseDirectory(final File inputDirectory, final Cobol85SourceFormatEnum format) throws IOException {
		if (inputDirectory.isDirectory() && !inputDirectory.isHidden()) {
			for (final File inputFile : inputDirectory.listFiles()) {
				if (inputFile.isFile() && !inputFile.isHidden() && isCobolFile(inputFile)) {
					parseFile(inputFile, format);
				}
			}
		}
	}

	@Override
	public void parseFile(final File inputFile, final Cobol85SourceFormatEnum format) throws IOException {
		final File libDirectory = inputFile.getParentFile();
		final String preProcessedInput = Cobol85GrammarContext.getInstance().getCobol85Preprocessor().process(inputFile,
				libDirectory, null, format);

		LOG.info("Parsing file {}.", inputFile.getName());

		doParse(preProcessedInput, inputFile);
	}

}
