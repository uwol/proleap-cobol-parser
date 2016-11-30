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
import io.proleap.cobol.parser.metamodel.CompilationUnit;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.impl.ProgramImpl;
import io.proleap.cobol.parser.runner.CobolParserRunner;
import io.proleap.cobol.parser.visitor.ParserVisitor;
import io.proleap.cobol.parser.visitor.impl.CobolCompilationUnitVisitorImpl;
import io.proleap.cobol.parser.visitor.impl.CobolDataDivisionVisitorImpl;
import io.proleap.cobol.parser.visitor.impl.CobolEnvironmentDivisionVisitorImpl;
import io.proleap.cobol.parser.visitor.impl.CobolIdentificationDivisionVisitorImpl;
import io.proleap.cobol.parser.visitor.impl.CobolProcedureDivisionVisitorImpl;
import io.proleap.cobol.parser.visitor.impl.CobolProcedureStatementVisitorImpl;
import io.proleap.cobol.parser.visitor.impl.CobolProgramUnitVisitorImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolDialect;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormat;

public class CobolParserRunnerImpl implements CobolParserRunner {

	protected final static Logger LOG = LogManager.getLogger(CobolParserRunnerImpl.class);

	protected void analyze(final Program program) {
		analyzeProgramUnits(program);

		analyzeIdentificationDivisions(program);
		analyzeEnvironmentDivisions(program);
		analyzeDataDivisions(program);
		analyzeProcedureDivisions(program);

		analyzeProcedureStatements(program);
	}

	protected void analyzeDataDivisions(final Program program) {
		for (final CompilationUnit compilationUnit : program.getCompilationUnits()) {
			final ParserVisitor visitor = new CobolDataDivisionVisitorImpl();

			LOG.info("Analyzing data divisions of compilation unit {}.", compilationUnit.getName());
			visitor.visit(compilationUnit.getCtx());
		}
	}

	@Override
	public Program analyzeDirectory(final File inputDirectory, final CobolSourceFormat format) throws IOException {
		return analyzeDirectory(inputDirectory, format, null);
	}

	@Override
	public Program analyzeDirectory(final File inputDirectory, final CobolSourceFormat format,
			final CobolDialect dialect) throws IOException {
		final Program program = new ProgramImpl();

		for (final File inputFile : inputDirectory.listFiles()) {
			parseFile(inputFile, program, format, dialect);
		}

		analyze(program);

		return program;
	}

	protected void analyzeEnvironmentDivisions(final Program program) {
		for (final CompilationUnit compilationUnit : program.getCompilationUnits()) {
			final ParserVisitor visitor = new CobolEnvironmentDivisionVisitorImpl();

			LOG.info("Analyzing environment divisions of compilation unit {}.", compilationUnit.getName());
			visitor.visit(compilationUnit.getCtx());
		}
	}

	@Override
	public Program analyzeFile(final File inputFile, final CobolSourceFormat format) throws IOException {
		return analyzeFile(inputFile, format, null);
	}

	@Override
	public Program analyzeFile(final File inputFile, final CobolSourceFormat format, final CobolDialect dialect)
			throws IOException {
		final Program program = new ProgramImpl();

		parseFile(inputFile, program, format, dialect);
		analyze(program);

		return program;
	}

	protected void analyzeIdentificationDivisions(final Program program) {
		for (final CompilationUnit compilationUnit : program.getCompilationUnits()) {
			final ParserVisitor visitor = new CobolIdentificationDivisionVisitorImpl();

			LOG.info("Analyzing identification divisions of compilation unit {}.", compilationUnit.getName());
			visitor.visit(compilationUnit.getCtx());
		}
	}

	protected void analyzeProcedureDivisions(final Program program) {
		for (final CompilationUnit compilationUnit : program.getCompilationUnits()) {
			final ParserVisitor visitor = new CobolProcedureDivisionVisitorImpl();

			LOG.info("Analyzing procedure divisions of compilation unit {}.", compilationUnit.getName());
			visitor.visit(compilationUnit.getCtx());
		}
	}

	protected void analyzeProcedureStatements(final Program program) {
		for (final CompilationUnit compilationUnit : program.getCompilationUnits()) {
			final ParserVisitor visitor = new CobolProcedureStatementVisitorImpl();

			LOG.info("Analyzing statements of compilation unit {}.", compilationUnit.getName());
			visitor.visit(compilationUnit.getCtx());
		}
	}

	protected void analyzeProgramUnits(final Program program) {
		for (final CompilationUnit compilationUnit : program.getCompilationUnits()) {
			final ParserVisitor visitor = new CobolProgramUnitVisitorImpl(compilationUnit);

			LOG.info("Analyzing program units of compilation unit {}.", compilationUnit.getName());
			visitor.visit(compilationUnit.getCtx());
		}
	}

	protected String getCompilationUnitName(final File inputFile) {
		return StringUtils.capitalize(FilenameUtils.removeExtension(inputFile.getName()));
	}

	protected boolean isCobolFile(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return "cbl".equals(extension);
	}

	protected boolean isRelevant(final File inputFile) {
		return inputFile.isFile() && !inputFile.isHidden() && isCobolFile(inputFile);
	}

	protected void parseFile(final File inputFile, final Program program, final CobolSourceFormat format,
			final CobolDialect dialect) throws IOException {
		if (isRelevant(inputFile)) {
			final File libDirectory = inputFile.getParentFile();

			// preprocess input stream
			final String preProcessedInput = CobolGrammarContext.getInstance().getCobolPreprocessor().process(inputFile,
					libDirectory, format, dialect);

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
			final String compilationUnitName = getCompilationUnitName(inputFile);

			// analyze contained copy books
			final ParserVisitor visitor = new CobolCompilationUnitVisitorImpl(program, compilationUnitName);

			LOG.info("Collecting units in file {}.", inputFile.getName());
			visitor.visit(ctx);
		} else {
			LOG.info("Ignoring file {}", inputFile.getAbsoluteFile());
		}
	}
}
