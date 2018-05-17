/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.visitor.impl;

import io.proleap.cobol.CobolParser;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.ProgramUnit;

/**
 * Visitor for collecting units in the AST.
 */
public class CobolProgramUnitVisitorImpl extends AbstractCobolParserVisitorImpl {

	protected final CompilationUnit compilationUnit;

	public CobolProgramUnitVisitorImpl(final CompilationUnit compilationUnit) {
		super(compilationUnit.getProgram());

		this.compilationUnit = compilationUnit;
	}

	@Override
	public Boolean visitDataDivision(final CobolParser.DataDivisionContext ctx) {
		final ProgramUnit programUnit = findProgramUnit(ctx);

		programUnit.addDataDivision(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitEnvironmentDivision(final CobolParser.EnvironmentDivisionContext ctx) {
		final ProgramUnit programUnit = findProgramUnit(ctx);

		programUnit.addEnvironmentDivision(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitIdentificationDivision(final CobolParser.IdentificationDivisionContext ctx) {
		final ProgramUnit programUnit = findProgramUnit(ctx);

		programUnit.addIdentificationDivision(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitProcedureDivision(final CobolParser.ProcedureDivisionContext ctx) {
		final ProgramUnit programUnit = findProgramUnit(ctx);

		programUnit.addProcedureDivision(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitProgramUnit(final CobolParser.ProgramUnitContext ctx) {
		compilationUnit.addProgramUnit(ctx);

		return visitChildren(ctx);
	}
}
