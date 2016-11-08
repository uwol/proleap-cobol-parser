/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.visitor.impl;

import org.antlr.v4.runtime.misc.NotNull;

import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;

/**
 * Visitor for analyzing expressions in the AST.
 */
public class CobolExpressionVisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolExpressionVisitorImpl(final CopyBook copyBook) {
		super(copyBook);
	}

	@Override
	public Boolean visitDisplayStatement(@NotNull final Cobol85Parser.DisplayStatementContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addDisplayStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLiteral(@NotNull final Cobol85Parser.LiteralContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addLiteral(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitMoveToStatement(@NotNull final Cobol85Parser.MoveToStatementContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addMoveToStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPerformProcedureStatement(@NotNull final Cobol85Parser.PerformProcedureStatementContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addPerformProcedureStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPerformStatement(@NotNull final Cobol85Parser.PerformStatementContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addPerformStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitStopStatement(@NotNull final Cobol85Parser.StopStatementContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addStopStatement(ctx);

		return visitChildren(ctx);
	}

}
