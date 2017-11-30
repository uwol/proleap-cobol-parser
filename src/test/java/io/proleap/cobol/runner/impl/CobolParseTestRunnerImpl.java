/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.runner.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.Trees;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import io.proleap.cobol.Cobol85Lexer;
import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.Cobol85Parser.StartRuleContext;
import io.proleap.cobol.ThrowingErrorListener;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.CobolPreprocessorParams;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;
import io.proleap.cobol.runner.CobolParseTestRunner;

/**
 * Cobol 85 parse runner for JUnit tests.
 */
public class CobolParseTestRunnerImpl implements CobolParseTestRunner {

	private final static Logger LOG = LogManager.getLogger(CobolParseTestRunnerImpl.class);

	public final static String TREE_SUFFIX = ".tree";

	protected void doCompareParseTree(final File treeFile, final StartRuleContext startRule, final Cobol85Parser parser)
			throws IOException {
		final String treeFileData = FileUtils.readFileToString(treeFile);

		if (!Strings.isBlank(treeFileData)) {
			LOG.info("Comparing parse tree with file {}.", treeFile.getName());

			final String inputFileTree = Trees.toStringTree(startRule, parser);
			final String cleanedInputFileTree = io.proleap.cobol.util.StringUtils.cleanFileTree(inputFileTree);
			final String cleanedTreeFileData = io.proleap.cobol.util.StringUtils.cleanFileTree(treeFileData);

			assertEquals(cleanedTreeFileData, cleanedInputFileTree);
		} else {
			LOG.info("Ignoring empty parse tree file {}.", treeFile.getName());
		}
	}

	protected void doParse(final String preProcessedInput, final File inputFile) throws IOException {
		final Cobol85Lexer lexer = new Cobol85Lexer(CharStreams.fromString(preProcessedInput));

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
	public void parseFile(final File inputFile, final CobolSourceFormatEnum format) throws IOException {
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, format);

		LOG.info("Parsing file {}.", inputFile.getName());

		doParse(preProcessedInput, inputFile);
	}

	@Override
	public void parseFile(final File inputFile, final List<File> copyFiles, final CobolSourceFormatEnum format,
			final CobolPreprocessorParams params) throws IOException {
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, format, params);

		LOG.info("Parsing file {}.", inputFile.getName());

		doParse(preProcessedInput, inputFile);
	}
}
