/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.AcceptStatementContext;
import io.proleap.cobol.Cobol85Parser.AddStatementContext;
import io.proleap.cobol.Cobol85Parser.AlterStatementContext;
import io.proleap.cobol.Cobol85Parser.CallStatementContext;
import io.proleap.cobol.Cobol85Parser.CancelStatementContext;
import io.proleap.cobol.Cobol85Parser.CloseStatementContext;
import io.proleap.cobol.Cobol85Parser.ComputeStatementContext;
import io.proleap.cobol.Cobol85Parser.ContinueStatementContext;
import io.proleap.cobol.Cobol85Parser.DeleteStatementContext;
import io.proleap.cobol.Cobol85Parser.DisableStatementContext;
import io.proleap.cobol.Cobol85Parser.DisplayStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideStatementContext;
import io.proleap.cobol.Cobol85Parser.EnableStatementContext;
import io.proleap.cobol.Cobol85Parser.EntryStatementContext;
import io.proleap.cobol.Cobol85Parser.EvaluateStatementContext;
import io.proleap.cobol.Cobol85Parser.ExecCicsStatementContext;
import io.proleap.cobol.Cobol85Parser.ExecSqlImsStatementContext;
import io.proleap.cobol.Cobol85Parser.ExecSqlStatementContext;
import io.proleap.cobol.Cobol85Parser.ExhibitStatementContext;
import io.proleap.cobol.Cobol85Parser.ExitStatementContext;
import io.proleap.cobol.Cobol85Parser.GenerateStatementContext;
import io.proleap.cobol.Cobol85Parser.GoToStatementContext;
import io.proleap.cobol.Cobol85Parser.GobackStatementContext;
import io.proleap.cobol.Cobol85Parser.IfStatementContext;
import io.proleap.cobol.Cobol85Parser.InitializeStatementContext;
import io.proleap.cobol.Cobol85Parser.InitiateStatementContext;
import io.proleap.cobol.Cobol85Parser.InspectStatementContext;
import io.proleap.cobol.Cobol85Parser.MergeStatementContext;
import io.proleap.cobol.Cobol85Parser.MoveStatementContext;
import io.proleap.cobol.Cobol85Parser.MultiplyStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenStatementContext;
import io.proleap.cobol.Cobol85Parser.PerformStatementContext;
import io.proleap.cobol.Cobol85Parser.PurgeStatementContext;
import io.proleap.cobol.Cobol85Parser.ReadStatementContext;
import io.proleap.cobol.Cobol85Parser.ReceiveStatementContext;
import io.proleap.cobol.Cobol85Parser.ReleaseStatementContext;
import io.proleap.cobol.Cobol85Parser.ReturnStatementContext;
import io.proleap.cobol.Cobol85Parser.RewriteStatementContext;
import io.proleap.cobol.Cobol85Parser.SearchStatementContext;
import io.proleap.cobol.Cobol85Parser.SendStatementContext;
import io.proleap.cobol.Cobol85Parser.SetStatementContext;
import io.proleap.cobol.Cobol85Parser.SortStatementContext;
import io.proleap.cobol.Cobol85Parser.StartStatementContext;
import io.proleap.cobol.Cobol85Parser.StatementContext;
import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.Cobol85Parser.StringStatementContext;
import io.proleap.cobol.Cobol85Parser.SubtractStatementContext;
import io.proleap.cobol.Cobol85Parser.TerminateStatementContext;
import io.proleap.cobol.Cobol85Parser.UnstringStatementContext;
import io.proleap.cobol.Cobol85Parser.WriteStatementContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.asg.metamodel.procedure.alter.AlterStatement;
import io.proleap.cobol.asg.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.asg.metamodel.procedure.cancel.CancelStatement;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseStatement;
import io.proleap.cobol.asg.metamodel.procedure.compute.ComputeStatement;
import io.proleap.cobol.asg.metamodel.procedure.continuestmt.ContinueStatement;
import io.proleap.cobol.asg.metamodel.procedure.delete.DeleteStatement;
import io.proleap.cobol.asg.metamodel.procedure.disable.DisableStatement;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideStatement;
import io.proleap.cobol.asg.metamodel.procedure.enable.EnableStatement;
import io.proleap.cobol.asg.metamodel.procedure.entry.EntryStatement;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.EvaluateStatement;
import io.proleap.cobol.asg.metamodel.procedure.execcics.ExecCicsStatement;
import io.proleap.cobol.asg.metamodel.procedure.execsql.ExecSqlStatement;
import io.proleap.cobol.asg.metamodel.procedure.execsqlims.ExecSqlImsStatement;
import io.proleap.cobol.asg.metamodel.procedure.exhibit.ExhibitStatement;
import io.proleap.cobol.asg.metamodel.procedure.exit.ExitStatement;
import io.proleap.cobol.asg.metamodel.procedure.generate.GenerateStatement;
import io.proleap.cobol.asg.metamodel.procedure.goback.GobackStatement;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.GoToStatement;
import io.proleap.cobol.asg.metamodel.procedure.ifstmt.IfStatement;
import io.proleap.cobol.asg.metamodel.procedure.initialize.InitializeStatement;
import io.proleap.cobol.asg.metamodel.procedure.initiate.InitiateStatement;
import io.proleap.cobol.asg.metamodel.procedure.inspect.InspectStatement;
import io.proleap.cobol.asg.metamodel.procedure.merge.MergeStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.metamodel.procedure.multiply.MultiplyStatement;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.asg.metamodel.procedure.purge.PurgeStatement;
import io.proleap.cobol.asg.metamodel.procedure.read.ReadStatement;
import io.proleap.cobol.asg.metamodel.procedure.receive.ReceiveStatement;
import io.proleap.cobol.asg.metamodel.procedure.release.ReleaseStatement;
import io.proleap.cobol.asg.metamodel.procedure.returnstmt.ReturnStatement;
import io.proleap.cobol.asg.metamodel.procedure.rewrite.RewriteStatement;
import io.proleap.cobol.asg.metamodel.procedure.search.SearchStatement;
import io.proleap.cobol.asg.metamodel.procedure.send.SendStatement;
import io.proleap.cobol.asg.metamodel.procedure.set.SetStatement;
import io.proleap.cobol.asg.metamodel.procedure.sort.SortStatement;
import io.proleap.cobol.asg.metamodel.procedure.start.StartStatement;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.metamodel.procedure.string.StringStatement;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractStatement;
import io.proleap.cobol.asg.metamodel.procedure.terminate.TerminateStatement;
import io.proleap.cobol.asg.metamodel.procedure.unstring.UnstringStatement;
import io.proleap.cobol.asg.metamodel.procedure.write.WriteStatement;

public interface Scope extends CobolDivisionElement {

	AcceptStatement addAcceptStatement(AcceptStatementContext ctx);

	AddStatement addAddStatement(AddStatementContext ctx);

	AlterStatement addAlterStatement(AlterStatementContext ctx);

	CallStatement addCallStatement(CallStatementContext ctx);

	CancelStatement addCancelStatement(CancelStatementContext ctx);

	CloseStatement addCloseStatement(CloseStatementContext ctx);

	ComputeStatement addComputeStatement(ComputeStatementContext ctx);

	ContinueStatement addContinueStatement(ContinueStatementContext ctx);

	DeleteStatement addDeleteStatement(DeleteStatementContext ctx);

	DisableStatement addDisableStatement(DisableStatementContext ctx);

	DisplayStatement addDisplayStatement(DisplayStatementContext ctx);

	DivideStatement addDivideStatement(DivideStatementContext ctx);

	EnableStatement addEnableStatement(EnableStatementContext ctx);

	EntryStatement addEntryStatement(EntryStatementContext ctx);

	EvaluateStatement addEvaluateStatement(EvaluateStatementContext ctx);

	ExecCicsStatement addExecCicsStatement(ExecCicsStatementContext ctx);

	ExecSqlImsStatement addExecSqlImsStatement(ExecSqlImsStatementContext ctx);

	ExecSqlStatement addExecSqlStatement(ExecSqlStatementContext ctx);

	ExhibitStatement addExhibitStatement(ExhibitStatementContext ctx);

	ExitStatement addExitStatement(ExitStatementContext ctx);

	GenerateStatement addGenerateStatement(GenerateStatementContext ctx);

	GobackStatement addGobackStatement(GobackStatementContext ctx);

	GoToStatement addGoToStatement(GoToStatementContext ctx);

	IfStatement addIfStatement(IfStatementContext ctx);

	InitializeStatement addInitializeStatement(InitializeStatementContext ctx);

	InitiateStatement addInitiateStatement(InitiateStatementContext ctx);

	InspectStatement addInspectStatement(InspectStatementContext ctx);

	MergeStatement addMergeStatement(MergeStatementContext ctx);

	MoveStatement addMoveStatement(MoveStatementContext ctx);

	MultiplyStatement addMultiplyStatement(MultiplyStatementContext ctx);

	OpenStatement addOpenStatement(OpenStatementContext ctx);

	PerformStatement addPerformStatement(PerformStatementContext ctx);

	PurgeStatement addPurgeStatement(PurgeStatementContext ctx);

	ReadStatement addReadStatement(ReadStatementContext ctx);

	ReceiveStatement addReceiveStatement(ReceiveStatementContext ctx);

	ReleaseStatement addReleaseStatement(ReleaseStatementContext ctx);

	ReturnStatement addReturnStatement(ReturnStatementContext ctx);

	RewriteStatement addRewriteStatement(RewriteStatementContext ctx);

	SearchStatement addSearchStatement(SearchStatementContext ctx);

	SendStatement addSendStatement(SendStatementContext ctx);

	SetStatement addSetStatement(SetStatementContext ctx);

	SortStatement addSortStatement(SortStatementContext ctx);

	StartStatement addStartStatement(StartStatementContext ctx);

	Statement addStatement(StatementContext ctx);

	StopStatement addStopStatement(StopStatementContext ctx);

	StringStatement addStringStatement(StringStatementContext ctx);

	SubtractStatement addSubtractStatement(SubtractStatementContext ctx);

	TerminateStatement addTerminateStatement(TerminateStatementContext ctx);

	UnstringStatement addUnstringStatement(UnstringStatementContext ctx);

	WriteStatement addWriteStatement(WriteStatementContext ctx);

	List<Statement> getStatements();
}
