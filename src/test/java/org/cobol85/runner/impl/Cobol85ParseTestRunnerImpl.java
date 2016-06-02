/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package org.cobol85.runner.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cobol85.Cobol85Lexer;
import org.cobol85.Cobol85Parser;
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

	protected static boolean isCobolFile(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return inputFile.isFile() && Arrays.asList(cobolFileExtensions).contains(extension);
	}

	protected void doParse(final String preProcessedInput) {
		// run the lexer
		final Cobol85Lexer lexer = new Cobol85Lexer(new ANTLRInputStream(preProcessedInput));

		lexer.removeErrorListeners();
		lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final Cobol85Parser parser = new Cobol85Parser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(ThrowingErrorListener.INSTANCE);

		parser.startRule();
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

		// preprocess input stream
		final String preProcessedInput = Cobol85GrammarContext.getInstance().getCobol85Preprocessor().process(inputFile,
				libDirectory, null, format);

		LOG.info("Parsing file {}.", inputFile.getName());

		doParse(preProcessedInput);
	}

	@Override
	public void parseString(final String inputString, final File libDirectory, final Cobol85SourceFormatEnum format) {
		// preprocess input stream
		final String preProcessedInput = Cobol85GrammarContext.getInstance().getCobol85Preprocessor()
				.process(inputString, libDirectory, null, format);

		LOG.info("Parsing string.");

		doParse(preProcessedInput);
	}
}
