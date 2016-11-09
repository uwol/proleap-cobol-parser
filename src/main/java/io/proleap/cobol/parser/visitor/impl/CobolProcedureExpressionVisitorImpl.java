/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.visitor.impl;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.registry.ASGElementRegistry;

/**
 * Visitor for analyzing expressions in the AST.
 */
public class CobolProcedureExpressionVisitorImpl extends AbstractCobolParserVisitorImpl {

	protected ProcedureDivision findProcedureDivision(final ParseTree ctx) {
		final ASGElementRegistry registry = CobolParserContext.getInstance().getASGElementRegistry();
		return CobolParserContext.getInstance().getAstTraverser().findParent(ProcedureDivision.class, ctx, registry);
	}

	@Override
	public Boolean visitDisplayStatement(@NotNull final Cobol85Parser.DisplayStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addDisplayStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitMoveToStatement(@NotNull final Cobol85Parser.MoveToStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addMoveToStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPerformProcedureStatement(@NotNull final Cobol85Parser.PerformProcedureStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addPerformProcedureStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPerformStatement(@NotNull final Cobol85Parser.PerformStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addPerformStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitStopStatement(@NotNull final Cobol85Parser.StopStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addStopStatement(ctx);

		return visitChildren(ctx);
	}

}
