/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.runner.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.CobolLexer;
import io.proleap.cobol.CobolParser;
import io.proleap.cobol.CobolParser.StartRuleContext;
import io.proleap.cobol.asg.exception.CobolParserException;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.impl.ProgramImpl;
import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.asg.params.impl.CobolParserParamsImpl;
import io.proleap.cobol.asg.runner.CobolParserRunner;
import io.proleap.cobol.asg.runner.ThrowingErrorListener;
import io.proleap.cobol.asg.visitor.ParserVisitor;
import io.proleap.cobol.asg.visitor.impl.CobolCompilationUnitVisitorImpl;
import io.proleap.cobol.asg.visitor.impl.CobolDataDivisionStep1VisitorImpl;
import io.proleap.cobol.asg.visitor.impl.CobolDataDivisionStep2VisitorImpl;
import io.proleap.cobol.asg.visitor.impl.CobolFileControlClauseVisitorImpl;
import io.proleap.cobol.asg.visitor.impl.CobolFileDescriptionEntryClauseVisitorImpl;
import io.proleap.cobol.asg.visitor.impl.CobolProcedureDivisionVisitorImpl;
import io.proleap.cobol.asg.visitor.impl.CobolProcedureStatementVisitorImpl;
import io.proleap.cobol.asg.visitor.impl.CobolProgramUnitVisitorImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;

public class CobolParserRunnerImpl implements CobolParserRunner {

	private final static Logger LOG = LoggerFactory.getLogger(CobolParserRunnerImpl.class);

	protected void analyze(final Program program) {
		analyzeProgramUnits(program);

		analyzeDataDivisionsStep1(program);
		analyzeDataDivisionsStep2(program);

		analyzeFileControlClauses(program);
		analyzeFileDescriptionEntriesClauses(program);

		analyzeProcedureDivisions(program);
		analyzeProcedureStatements(program);
	}

	@Override
	public Program analyzeCode(final String cobolCode, final String compilationUnitName, final CobolParserParams params)
			throws IOException {
		final Program program = new ProgramImpl();

		parseCode(cobolCode, compilationUnitName, program, params);
		analyze(program);

		return program;
	}

	protected void analyzeDataDivisionsStep1(final Program program) {
		for (final CompilationUnit compilationUnit : program.getCompilationUnits()) {
			final ParserVisitor visitor = new CobolDataDivisionStep1VisitorImpl(program);

			LOG.info("Analyzing data divisions of compilation unit {} in step 1.", compilationUnit.getName());
			visitor.visit(compilationUnit.getCtx());
		}
	}

	protected void analyzeDataDivisionsStep2(final Program program) {
		for (final CompilationUnit compilationUnit : program.getCompilationUnits()) {
			final ParserVisitor visitor = new CobolDataDivisionStep2VisitorImpl(program);

			LOG.info("Analyzing data divisions of compilation unit {} in step 2.", compilationUnit.getName());
			visitor.visit(compilationUnit.getCtx());
		}
	}

	@Override
	public Program analyzeFile(final File inputFile, final CobolParserParams params) throws IOException {
		final Program program = new ProgramImpl();

		parseFile(inputFile, program, params);
		analyze(program);

		return program;
	}

	@Override
	public Program analyzeFile(final File cobolFile, final CobolSourceFormatEnum format) throws IOException {
		final CobolParserParams params = createDefaultParams(format, cobolFile);
		return analyzeFile(cobolFile, params);
	}

	protected void analyzeFileControlClauses(final Program program) {
		for (final CompilationUnit compilationUnit : program.getCompilationUnits()) {
			final ParserVisitor visitor = new CobolFileControlClauseVisitorImpl(program);

			LOG.info("Analyzing file control clauses of compilation unit {}.", compilationUnit.getName());
			visitor.visit(compilationUnit.getCtx());
		}
	}

	protected void analyzeFileDescriptionEntriesClauses(final Program program) {
		for (final CompilationUnit compilationUnit : program.getCompilationUnits()) {
			final ParserVisitor visitor = new CobolFileDescriptionEntryClauseVisitorImpl(program);

			LOG.info("Analyzing file description entries of compilation unit {}.", compilationUnit.getName());
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

	protected String capitalize(final String line) {
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}

	protected CobolParserParams createDefaultParams() {
		return new CobolParserParamsImpl();
	}

	protected CobolParserParams createDefaultParams(final CobolSourceFormatEnum format, final File cobolFile) {
		final CobolParserParams result = createDefaultParams();
		result.setFormat(format);

		final File copyBooksDirectory = cobolFile.getParentFile();
		result.setCopyBookDirectories(Arrays.asList(copyBooksDirectory));

		return result;
	}

	protected String getCompilationUnitName(final File cobolFile) {
		return capitalize(FilenameUtils.removeExtension(cobolFile.getName()));
	}

	protected void parseCode(final String cobolCode, final String compilationUnitName, final Program program,
			final CobolParserParams params) throws IOException {
		LOG.info("Parsing compilation unit {}.", compilationUnitName);

		// preprocess input stream
		final String preProcessedInput = new CobolPreprocessorImpl().process(cobolCode, params);

		parsePreprocessInput(preProcessedInput, compilationUnitName, program, params);
	}

	protected void parseFile(final File cobolFile, final Program program, final CobolParserParams params)
			throws IOException {
		if (!cobolFile.isFile()) {
			throw new CobolParserException("Could not find file " + cobolFile.getAbsolutePath());
		} else {
			// determine the copy book name
			final String compilationUnitName = getCompilationUnitName(cobolFile);

			LOG.info("Parsing compilation unit {}.", compilationUnitName);

			// preprocess input stream
			final String preProcessedInput = new CobolPreprocessorImpl().process(cobolFile, params);

			parsePreprocessInput(preProcessedInput, compilationUnitName, program, params);
		}
	}

	protected void parsePreprocessInput(final String preProcessedInput, final String compilationUnitName,
			final Program program, final CobolParserParams params) throws IOException {
		// run the lexer
		final CobolLexer lexer = new CobolLexer(CharStreams.fromString(preProcessedInput));

		if (!params.getIgnoreSyntaxErrors()) {
			// register an error listener, so that preprocessing stops on errors
			lexer.removeErrorListeners();
			lexer.addErrorListener(new ThrowingErrorListener());
		}

		// get a list of matched tokens
		final CommonTokenStream tokens = new CommonTokenStream(lexer);

		// pass the tokens to the parser
		final CobolParser parser = new CobolParser(tokens);

		if (!params.getIgnoreSyntaxErrors()) {
			// register an error listener, so that preprocessing stops on errors
			parser.removeErrorListeners();
			parser.addErrorListener(new ThrowingErrorListener());
		}

		// specify our entry point
		final StartRuleContext ctx = parser.startRule();

		// analyze contained compilation units
		final List<String> lines = splitLines(preProcessedInput);
		final ParserVisitor visitor = new CobolCompilationUnitVisitorImpl(compilationUnitName, lines, tokens, program);

		visitor.visit(ctx);
	}

	protected List<String> splitLines(final String preProcessedInput) {
		final Scanner scanner = new Scanner(preProcessedInput);
		final List<String> result = new ArrayList<String>();

		while (scanner.hasNextLine()) {
			result.add(scanner.nextLine());
		}

		scanner.close();
		return result;
	}
}
