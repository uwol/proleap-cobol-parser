/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.visitor.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.Cobol85Parser;
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
	public Boolean visitAcceptStatement(final Cobol85Parser.AcceptStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addAcceptStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitAddStatement(final Cobol85Parser.AddStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addAddStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitAlterStatement(final Cobol85Parser.AlterStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addAlterStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCallStatement(final Cobol85Parser.CallStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCallStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCancelStatement(final Cobol85Parser.CancelStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCancelStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCloseStatement(final Cobol85Parser.CloseStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCloseStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitComputeStatement(final Cobol85Parser.ComputeStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addComputeStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitContinueStatement(final Cobol85Parser.ContinueStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addContinueStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDeleteStatement(final Cobol85Parser.DeleteStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addDeleteStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDisableStatement(final Cobol85Parser.DisableStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addDisableStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDisplayStatement(final Cobol85Parser.DisplayStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addDisplayStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDivideStatement(final Cobol85Parser.DivideStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addDivideStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitEjectStatement(final Cobol85Parser.EjectStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addEjectStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitEnableStatement(final Cobol85Parser.EnableStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addEnableStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitEntryStatement(final Cobol85Parser.EntryStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addEntryStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitEvaluateStatement(final Cobol85Parser.EvaluateStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addEvaluateStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExecCicsStatement(final Cobol85Parser.ExecCicsStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addExecCicsStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExecSqlImsStatement(final Cobol85Parser.ExecSqlImsStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addExecSqlImsStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExecSqlStatement(final Cobol85Parser.ExecSqlStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addExecSqlStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExhibitStatement(final Cobol85Parser.ExhibitStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addExhibitStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExitStatement(final Cobol85Parser.ExitStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addExitStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitGenerateStatement(final Cobol85Parser.GenerateStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addGenerateStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitGobackStatement(final Cobol85Parser.GobackStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addGobackStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitGoToStatement(final Cobol85Parser.GoToStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addGoToStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitIfStatement(final Cobol85Parser.IfStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addIfStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitInitializeStatement(final Cobol85Parser.InitializeStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addInitializeStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitInitiateStatement(final Cobol85Parser.InitiateStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addInitiateStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitInspectStatement(final Cobol85Parser.InspectStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addInspectStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitMergeStatement(final Cobol85Parser.MergeStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addMergeStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitMoveStatement(final Cobol85Parser.MoveStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addMoveStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitMultiplyStatement(final Cobol85Parser.MultiplyStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addMultiplyStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOpenStatement(final Cobol85Parser.OpenStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addOpenStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPerformStatement(final Cobol85Parser.PerformStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addPerformStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPurgeStatement(final Cobol85Parser.PurgeStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addPurgeStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReadStatement(final Cobol85Parser.ReadStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addReadStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReceiveStatement(final Cobol85Parser.ReceiveStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addReceiveStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReleaseStatement(final Cobol85Parser.ReleaseStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addReleaseStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReturnStatement(final Cobol85Parser.ReturnStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addReturnStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRewriteStatement(final Cobol85Parser.RewriteStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addRewriteStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSearchStatement(final Cobol85Parser.SearchStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSearchStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSendStatement(final Cobol85Parser.SendStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSendStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSetStatement(final Cobol85Parser.SetStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSetStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSkipStatement(final Cobol85Parser.SkipStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSkipStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSortStatement(final Cobol85Parser.SortStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSortStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitStartStatement(final Cobol85Parser.StartStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addStartStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitStopStatement(final Cobol85Parser.StopStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addStopStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitStringStatement(final Cobol85Parser.StringStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addStringStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSubtractStatement(final Cobol85Parser.SubtractStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSubtractStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitTerminateStatement(final Cobol85Parser.TerminateStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addTerminateStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitUnstringStatement(final Cobol85Parser.UnstringStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addUnstringStatement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitWriteStatement(final Cobol85Parser.WriteStatementContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addWriteStatement(ctx);

		return visitChildren(ctx);
	}

}
