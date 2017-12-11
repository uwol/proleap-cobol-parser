/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.visitor.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Section;
import io.proleap.cobol.asg.util.ANTLRUtils;

/**
 * Visitor for analyzing declarations in the AST.
 */
public class CobolProcedureDivisionVisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolProcedureDivisionVisitorImpl(final Program program) {
		super(program);
	}

	protected ProcedureDivision findProcedureDivision(final ParseTree ctx) {
		return ANTLRUtils.findParent(ProcedureDivision.class, ctx, program.getASGElementRegistry());
	}

	@Override
	public Boolean visitParagraph(final Cobol85Parser.ParagraphContext ctx) {
		final Scope scope = findScope(ctx);

		if (scope instanceof ProcedureDivision) {
			final ProcedureDivision procedureDivision = (ProcedureDivision) scope;
			procedureDivision.addParagraph(ctx);
		} else if (scope instanceof Section) {
			final Section section = (Section) scope;
			section.addParagraph(ctx);
		}

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitParagraphName(final Cobol85Parser.ParagraphNameContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addParagraphName(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitProcedureDeclaratives(final Cobol85Parser.ProcedureDeclarativesContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addDeclaratives(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitProcedureDivisionGivingClause(final Cobol85Parser.ProcedureDivisionGivingClauseContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addGivingClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitProcedureDivisionUsingClause(final Cobol85Parser.ProcedureDivisionUsingClauseContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addUsingClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitProcedureSection(final Cobol85Parser.ProcedureSectionContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addSection(ctx);

		return visitChildren(ctx);
	}
}
