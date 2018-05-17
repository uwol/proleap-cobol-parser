/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.visitor.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.CobolParser;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.util.ANTLRUtils;

/**
 * Visitor for analyzing statements in the AST.
 */
public class CobolProcedureStatementVisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolProcedureStatementVisitorImpl(final Program program) {
		super(program);
	}

	protected ProcedureDivision findProcedureDivision(final ParseTree ctx) {
		return ANTLRUtils.findParent(ProcedureDivision.class, ctx, program.getASGElementRegistry());
	}

	@Override
	public Boolean visitAcceptStatement(final CobolParser.AcceptStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addAcceptStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitAddStatement(final CobolParser.AddStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addAddStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitAlterStatement(final CobolParser.AlterStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addAlterStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCallStatement(final CobolParser.CallStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCallStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCancelStatement(final CobolParser.CancelStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCancelStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCloseStatement(final CobolParser.CloseStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCloseStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitComputeStatement(final CobolParser.ComputeStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addComputeStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitContinueStatement(final CobolParser.ContinueStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addContinueStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDeleteStatement(final CobolParser.DeleteStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addDeleteStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDisableStatement(final CobolParser.DisableStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addDisableStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDisplayStatement(final CobolParser.DisplayStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addDisplayStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDivideStatement(final CobolParser.DivideStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addDivideStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitEnableStatement(final CobolParser.EnableStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addEnableStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitEntryStatement(final CobolParser.EntryStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addEntryStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitEvaluateStatement(final CobolParser.EvaluateStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addEvaluateStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExecCicsStatement(final CobolParser.ExecCicsStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addExecCicsStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExecSqlImsStatement(final CobolParser.ExecSqlImsStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addExecSqlImsStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExecSqlStatement(final CobolParser.ExecSqlStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addExecSqlStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExhibitStatement(final CobolParser.ExhibitStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addExhibitStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExitStatement(final CobolParser.ExitStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addExitStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitGenerateStatement(final CobolParser.GenerateStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addGenerateStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitGobackStatement(final CobolParser.GobackStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addGobackStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitGoToStatement(final CobolParser.GoToStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addGoToStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitIfStatement(final CobolParser.IfStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addIfStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitInitializeStatement(final CobolParser.InitializeStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addInitializeStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitInitiateStatement(final CobolParser.InitiateStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addInitiateStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitInspectStatement(final CobolParser.InspectStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addInspectStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitMergeStatement(final CobolParser.MergeStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addMergeStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitMoveStatement(final CobolParser.MoveStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addMoveStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitMultiplyStatement(final CobolParser.MultiplyStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addMultiplyStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOpenStatement(final CobolParser.OpenStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addOpenStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPerformStatement(final CobolParser.PerformStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addPerformStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPurgeStatement(final CobolParser.PurgeStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addPurgeStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReadStatement(final CobolParser.ReadStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addReadStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReceiveStatement(final CobolParser.ReceiveStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addReceiveStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReleaseStatement(final CobolParser.ReleaseStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addReleaseStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReturnStatement(final CobolParser.ReturnStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addReturnStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRewriteStatement(final CobolParser.RewriteStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addRewriteStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSearchStatement(final CobolParser.SearchStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSearchStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSendStatement(final CobolParser.SendStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSendStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSetStatement(final CobolParser.SetStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSetStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSortStatement(final CobolParser.SortStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSortStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitStartStatement(final CobolParser.StartStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addStartStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitStopStatement(final CobolParser.StopStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addStopStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitStringStatement(final CobolParser.StringStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addStringStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSubtractStatement(final CobolParser.SubtractStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSubtractStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitTerminateStatement(final CobolParser.TerminateStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addTerminateStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitUnstringStatement(final CobolParser.UnstringStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addUnstringStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitWriteStatement(final CobolParser.WriteStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addWriteStatement(ctx);

		return visitChildren(ctx);
	}

}
