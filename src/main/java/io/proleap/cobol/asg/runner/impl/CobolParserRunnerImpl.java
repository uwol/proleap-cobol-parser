/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.runner.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Lexer;
import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.Cobol85Parser.StartRuleContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.impl.ProgramImpl;
import io.proleap.cobol.asg.runner.CobolParserRunner;
import io.proleap.cobol.asg.visitor.ParserVisitor;
import io.proleap.cobol.asg.visitor.impl.CobolCompilationUnitVisitorImpl;
import io.proleap.cobol.asg.visitor.impl.CobolDataDivisionVisitorImpl;
import io.proleap.cobol.asg.visitor.impl.CobolEnvironmentDivisionVisitorImpl;
import io.proleap.cobol.asg.visitor.impl.CobolIdentificationDivisionVisitorImpl;
import io.proleap.cobol.asg.visitor.impl.CobolProcedureDivisionVisitorImpl;
import io.proleap.cobol.asg.visitor.impl.CobolProcedureStatementVisitorImpl;
import io.proleap.cobol.asg.visitor.impl.CobolProgramUnitVisitorImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolDialect;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;

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
			final ParserVisitor visitor = new CobolDataDivisionVisitorImpl(program);

			LOG.info("Analyzing data divisions of compilation unit {}.", compilationUnit.getName());
			visitor.visit(compilationUnit.getCtx());
		}
	}

	@Override
	public Program analyzeDirectory(final File inputDirectory, final CobolSourceFormatEnum format) throws IOException {
		return analyzeDirectory(inputDirectory, format, null);
	}

	@Override
	public Program analyzeDirectory(final File inputDirectory, final CobolSourceFormatEnum format,
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
			final ParserVisitor visitor = new CobolEnvironmentDivisionVisitorImpl(program);

			LOG.info("Analyzing environment divisions of compilation unit {}.", compilationUnit.getName());
			visitor.visit(compilationUnit.getCtx());
		}
	}

	@Override
	public Program analyzeFile(final File inputFile, final CobolSourceFormatEnum format) throws IOException {
		return analyzeFile(inputFile, format, null);
	}

	@Override
	public Program analyzeFile(final File inputFile, final CobolSourceFormatEnum format, final CobolDialect dialect)
			throws IOException {
		final Program program = new ProgramImpl();

		parseFile(inputFile, program, format, dialect);
		analyze(program);

		return program;
	}

	protected void analyzeIdentificationDivisions(final Program program) {
		for (final CompilationUnit compilationUnit : program.getCompilationUnits()) {
			final ParserVisitor visitor = new CobolIdentificationDivisionVisitorImpl(program);

			LOG.info("Analyzing identification divisions of compilation unit {}.", compilationUnit.getName());
			visitor.visit(compilationUnit.getCtx());
		}
	}

	protected void analyzeProcedureDivisions(final Program program) {
		for (final CompilationUnit compilationUnit : program.getCompilationUnits()) {
			final ParserVisitor visitor = new CobolProcedureDivisionVisitorImpl(program);

			LOG.info("Analyzing procedure divisions of compilation unit {}.", compilationUnit.getName());
			visitor.visit(compilationUnit.getCtx());
		}
	}

	protected void analyzeProcedureStatements(final Program program) {
		for (final CompilationUnit compilationUnit : program.getCompilationUnits()) {
			final ParserVisitor visitor = new CobolProcedureStatementVisitorImpl(program);

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

	protected void parseFile(final File inputFile, final Program program, final CobolSourceFormatEnum format,
			final CobolDialect dialect) throws IOException {
		if (!inputFile.isFile()) {
			LOG.warn("Could not find file {}", inputFile.getAbsolutePath());
		} else if (inputFile.isHidden()) {
			LOG.warn("Ignoring hidden file {}", inputFile.getAbsolutePath());
		} else if (!isCobolFile(inputFile)) {
			LOG.info("Ignoring file {} because of file extension.", inputFile.getAbsolutePath());
		} else {
			final File libDirectory = inputFile.getParentFile();

			// preprocess input stream
			final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, libDirectory, format,
					dialect);

			LOG.info("Parsing file {}.", inputFile.getName());

			// run the lexer
			final Cobol85Lexer lexer = new Cobol85Lexer(CharStreams.fromString(preProcessedInput));

			// get a list of matched tokens
			final CommonTokenStream tokens = new CommonTokenStream(lexer);

			// pass the tokens to the parser
			final Cobol85Parser parser = new Cobol85Parser(tokens);

			// specify our entry point
			final StartRuleContext ctx = parser.startRule();

			// determine the copy book name
			final String compilationUnitName = getCompilationUnitName(inputFile);

			// analyze contained compilation units
			final List<String> lines = splitLines(preProcessedInput);
			final ParserVisitor visitor = new CobolCompilationUnitVisitorImpl(compilationUnitName, lines, program);

			LOG.info("Collecting units in file {}.", inputFile.getName());
			visitor.visit(ctx);
		}
	}

	public List<String> splitLines(final String preProcessedInput) {
		final Scanner scanner = new Scanner(preProcessedInput);
		final List<String> result = new ArrayList<String>();

		while (scanner.hasNextLine()) {
			result.add(scanner.nextLine());
		}

		scanner.close();
		return result;
	}
}
