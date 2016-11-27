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
import io.proleap.cobol.parser.util.ANTLRUtils;

/**
 * Visitor for analyzing expressions in the AST.
 */
public class CobolProcedureExpressionVisitorImpl extends AbstractCobolParserVisitorImpl {

	protected ProcedureDivision findProcedureDivision(final ParseTree ctx) {
		final ASGElementRegistry registry = CobolParserContext.getInstance().getASGElementRegistry();
		return ANTLRUtils.findParent(ProcedureDivision.class, ctx, registry);
	}

	@Override
	public Boolean visitAcceptStatement(@NotNull final Cobol85Parser.AcceptStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addAcceptStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitAddStatement(@NotNull final Cobol85Parser.AddStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addAddStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitAlterStatement(@NotNull final Cobol85Parser.AlterStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addAlterStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCallStatement(@NotNull final Cobol85Parser.CallStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addCallStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCancelStatement(@NotNull final Cobol85Parser.CancelStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addCancelStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCloseStatement(@NotNull final Cobol85Parser.CloseStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addCloseStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitComputeStatement(@NotNull final Cobol85Parser.ComputeStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addComputeStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitContinueStatement(@NotNull final Cobol85Parser.ContinueStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addContinueStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDeleteStatement(@NotNull final Cobol85Parser.DeleteStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addDeleteStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDisableStatement(@NotNull final Cobol85Parser.DisableStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addDisableStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDisplayStatement(@NotNull final Cobol85Parser.DisplayStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addDisplayStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDivideStatement(@NotNull final Cobol85Parser.DivideStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addDivideStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitEnableStatement(@NotNull final Cobol85Parser.EnableStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addEnableStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitEntryStatement(@NotNull final Cobol85Parser.EntryStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addEntryStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExitStatement(@NotNull final Cobol85Parser.ExitStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addExitStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitGenerateStatement(@NotNull final Cobol85Parser.GenerateStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addGenerateStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitGobackStatement(@NotNull final Cobol85Parser.GobackStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addGobackStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitGoToStatement(@NotNull final Cobol85Parser.GoToStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addGoToStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitIfStatement(@NotNull final Cobol85Parser.IfStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addIfStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitInitializeStatement(@NotNull final Cobol85Parser.InitializeStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addInitializeStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitInitiateStatement(@NotNull final Cobol85Parser.InitiateStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addInitiateStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitInspectStatement(@NotNull final Cobol85Parser.InspectStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addInspectStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitMoveToStatement(@NotNull final Cobol85Parser.MoveToStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addMoveToStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitMultiplyStatement(@NotNull final Cobol85Parser.MultiplyStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addMultiplyStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOpenStatement(@NotNull final Cobol85Parser.OpenStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addOpenStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPerformStatement(@NotNull final Cobol85Parser.PerformStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addPerformStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPurgeStatement(@NotNull final Cobol85Parser.PurgeStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addPurgeStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReadStatement(@NotNull final Cobol85Parser.ReadStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addReadStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReceiveStatement(@NotNull final Cobol85Parser.ReceiveStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addReceiveStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReleaseStatement(@NotNull final Cobol85Parser.ReleaseStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addReleaseStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReturnStatement(@NotNull final Cobol85Parser.ReturnStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addReturnStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRewriteStatement(@NotNull final Cobol85Parser.RewriteStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addRewriteStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSearchStatement(@NotNull final Cobol85Parser.SearchStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addSearchStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitStartStatement(@NotNull final Cobol85Parser.StartStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addStartStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitStopStatement(@NotNull final Cobol85Parser.StopStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addStopStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitStringStatement(@NotNull final Cobol85Parser.StringStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addStringStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitTerminateStatement(@NotNull final Cobol85Parser.TerminateStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addTerminateStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitWriteStatement(@NotNull final Cobol85Parser.WriteStatementContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addWriteStatement(ctx);

		return visitChildren(ctx);
	}

}
