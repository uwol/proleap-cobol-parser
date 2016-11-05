/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.runner.impl;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Lexer;
import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.Cobol85Parser.StartRuleContext;
import io.proleap.cobol.applicationcontext.CobolGrammarContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.impl.ProgramImpl;
import io.proleap.cobol.parser.runner.CobolParserRunner;
import io.proleap.cobol.parser.visitor.ParserVisitor;
import io.proleap.cobol.parser.visitor.impl.CobolDeclarationVisitorImpl;
import io.proleap.cobol.parser.visitor.impl.CobolExpressionVisitorImpl;
import io.proleap.cobol.parser.visitor.impl.CobolUnitVisitorImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolDialect;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormat;

public class CobolParserRunnerImpl implements CobolParserRunner {

	protected final static Logger LOG = LogManager.getLogger(CobolParserRunnerImpl.class);

	protected void analyze(final Program program) {
		analyzeDeclarations(program);
		analyzeExpressions(program);
	}

	protected void analyzeDeclarations(final Program program) {
		for (final CopyBook copyBook : program.getCopyBooks()) {
			final ParserVisitor visitor = new CobolDeclarationVisitorImpl(copyBook);

			LOG.info("Analyzing declaration of copy book {}.", copyBook.getName());
			visitor.visit(copyBook.getCtx());
		}
	}

	@Override
	public Program analyzeDirectory(final File inputDirectory, final CobolDialect dialect,
			final CobolSourceFormat format) throws IOException {
		final Program program = new ProgramImpl();

		for (final File inputFile : inputDirectory.listFiles()) {
			parseFile(inputFile, program, dialect, format);
		}

		analyze(program);

		return program;
	}

	protected void analyzeExpressions(final Program program) {
		for (final CopyBook copyBook : program.getCopyBooks()) {
			final ParserVisitor visitor = new CobolExpressionVisitorImpl(copyBook);

			LOG.info("Analyzing expressions of copy book {}.", copyBook.getName());
			visitor.visit(copyBook.getCtx());
		}
	}

	@Override
	public Program analyzeFile(final File inputFile, final CobolDialect dialect, final CobolSourceFormat format)
			throws IOException {
		final Program program = new ProgramImpl();

		parseFile(inputFile, program, dialect, format);
		analyze(program);

		return program;
	}

	protected String getCopyBookName(final File inputFile) {
		return StringUtils.capitalize(FilenameUtils.removeExtension(inputFile.getName()));
	}

	protected boolean isCobolFile(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return "cbl".equals(extension);
	}

	protected boolean isRelevant(final File inputFile) {
		return inputFile.isFile() && !inputFile.isHidden() && isCobolFile(inputFile);
	}

	protected void parseFile(final File inputFile, final Program program, final CobolDialect dialect,
			final CobolSourceFormat format) throws IOException {
		if (isRelevant(inputFile)) {
			final File libDirectory = inputFile.getParentFile();

			// preprocess input stream
			final String preProcessedInput = CobolGrammarContext.getInstance().getCobolPreprocessor().process(inputFile,
					libDirectory, dialect, format);

			LOG.info("Parsing file {}.", inputFile.getName());

			// run the lexer
			final Cobol85Lexer lexer = new Cobol85Lexer(new ANTLRInputStream(preProcessedInput));

			// get a list of matched tokens
			final CommonTokenStream tokens = new CommonTokenStream(lexer);

			// pass the tokens to the parser
			final Cobol85Parser parser = new Cobol85Parser(tokens);

			// specify our entry point
			final StartRuleContext ctx = parser.startRule();

			// determine the copy book name
			final String copyBookName = getCopyBookName(inputFile);

			// analyze contained copy books
			final ParserVisitor visitor = new CobolUnitVisitorImpl(program, copyBookName);

			LOG.info("Collecting units in file {}.", inputFile.getName());
			visitor.visit(ctx);
		} else {
			LOG.info("Ignoring file {}.", inputFile.getName());
		}
	}
}
