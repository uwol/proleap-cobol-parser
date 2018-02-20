/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.runner.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.Trees;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import io.proleap.cobol.Cobol85Lexer;
import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.Cobol85Parser.StartRuleContext;
import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.asg.params.impl.CobolParserParamsImpl;
import io.proleap.cobol.asg.runner.ThrowingErrorListener;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;
import io.proleap.cobol.runner.CobolParseTestRunner;

/**
 * Cobol 85 parse runner for JUnit tests.
 */
public class CobolParseTestRunnerImpl implements CobolParseTestRunner {

	private final static Logger LOG = LoggerFactory.getLogger(CobolParseTestRunnerImpl.class);

	public final static String TREE_SUFFIX = ".tree";

	protected CobolParserParams createDefaultParams(final File cobolFile) {
		final CobolParserParams result = new CobolParserParamsImpl();

		final File copyBooksDirectory = cobolFile.getParentFile();
		result.setCopyBookDirectories(Lists.newArrayList(copyBooksDirectory));

		return result;
	}

	protected void doCompareParseTree(final File treeFile, final StartRuleContext startRule, final Cobol85Parser parser)
			throws IOException {
		final String treeFileData = FileUtils.readFileToString(treeFile);

		if (!StringUtils.isBlank(treeFileData)) {
			LOG.info("Comparing parse tree with file {}.", treeFile.getName());

			final String inputFileTree = Trees.toStringTree(startRule, parser);
			final String cleanedInputFileTree = io.proleap.cobol.util.CobolTestStringUtils.cleanFileTree(inputFileTree);
			final String cleanedTreeFileData = io.proleap.cobol.util.CobolTestStringUtils.cleanFileTree(treeFileData);

			assertEquals(cleanedTreeFileData, cleanedInputFileTree);
		} else {
			LOG.info("Ignoring empty parse tree file {}.", treeFile.getName());
		}
	}

	protected void doParse(final String preProcessedInput, final File inputFile, final CobolParserParams params)
			throws IOException {
		final Cobol85Lexer lexer = new Cobol85Lexer(CharStreams.fromString(preProcessedInput));

		if (!params.getIgnoreSyntaxErrors()) {
			lexer.removeErrorListeners();
			lexer.addErrorListener(new ThrowingErrorListener());
		}

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final Cobol85Parser parser = new Cobol85Parser(tokens);

		if (!params.getIgnoreSyntaxErrors()) {
			parser.removeErrorListeners();
			parser.addErrorListener(new ThrowingErrorListener());
		}

		final StartRuleContext startRule = parser.startRule();
		final File treeFile = new File(inputFile.getAbsolutePath() + TREE_SUFFIX);

		if (treeFile.exists()) {
			doCompareParseTree(treeFile, startRule, parser);
		}
	}

	@Override
	public void parseFile(final File cobolFile, final CobolSourceFormatEnum format) throws IOException {
		final String preProcessedInput = new CobolPreprocessorImpl().process(cobolFile, format);

		LOG.info("Parsing file {}.", cobolFile.getName());

		doParse(preProcessedInput, cobolFile, createDefaultParams(cobolFile));
	}
}
