/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure;

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
import io.proleap.cobol.Cobol85Parser.ExitStatementContext;
import io.proleap.cobol.Cobol85Parser.GenerateStatementContext;
import io.proleap.cobol.Cobol85Parser.GoToStatementContext;
import io.proleap.cobol.Cobol85Parser.GobackStatementContext;
import io.proleap.cobol.Cobol85Parser.IfStatementContext;
import io.proleap.cobol.Cobol85Parser.InitializeStatementContext;
import io.proleap.cobol.Cobol85Parser.InitiateStatementContext;
import io.proleap.cobol.Cobol85Parser.InspectStatementContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementContext;
import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphNameContext;
import io.proleap.cobol.Cobol85Parser.PerformStatementContext;
import io.proleap.cobol.Cobol85Parser.ProcedureDeclarativesContext;
import io.proleap.cobol.Cobol85Parser.PurgeStatementContext;
import io.proleap.cobol.Cobol85Parser.ReadStatementContext;
import io.proleap.cobol.Cobol85Parser.ReceiveStatementContext;
import io.proleap.cobol.Cobol85Parser.ReleaseStatementContext;
import io.proleap.cobol.Cobol85Parser.ReturnStatementContext;
import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.Cobol85Parser.TerminateStatementContext;
import io.proleap.cobol.Cobol85Parser.WriteStatementContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.procedure.accept.AcceptStatement;
import io.proleap.cobol.parser.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.parser.metamodel.procedure.alter.AlterStatement;
import io.proleap.cobol.parser.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.parser.metamodel.procedure.cancel.CancelStatement;
import io.proleap.cobol.parser.metamodel.procedure.close.CloseStatement;
import io.proleap.cobol.parser.metamodel.procedure.compute.ComputeStatement;
import io.proleap.cobol.parser.metamodel.procedure.continuestmt.ContinueStatement;
import io.proleap.cobol.parser.metamodel.procedure.declaratives.Declaratives;
import io.proleap.cobol.parser.metamodel.procedure.delete.DeleteStatement;
import io.proleap.cobol.parser.metamodel.procedure.disable.DisableStatement;
import io.proleap.cobol.parser.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.parser.metamodel.procedure.divide.DivideStatement;
import io.proleap.cobol.parser.metamodel.procedure.enable.EnableStatement;
import io.proleap.cobol.parser.metamodel.procedure.entry.EntryStatement;
import io.proleap.cobol.parser.metamodel.procedure.exit.ExitStatement;
import io.proleap.cobol.parser.metamodel.procedure.generate.GenerateStatement;
import io.proleap.cobol.parser.metamodel.procedure.goback.GobackStatement;
import io.proleap.cobol.parser.metamodel.procedure.gotostmt.GoToStatement;
import io.proleap.cobol.parser.metamodel.procedure.ifstmt.IfStatement;
import io.proleap.cobol.parser.metamodel.procedure.initialize.InitializeStatement;
import io.proleap.cobol.parser.metamodel.procedure.initiate.InitiateStatement;
import io.proleap.cobol.parser.metamodel.procedure.inspect.InspectStatement;
import io.proleap.cobol.parser.metamodel.procedure.move.MoveToStatement;
import io.proleap.cobol.parser.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.parser.metamodel.procedure.purge.PurgeStatement;
import io.proleap.cobol.parser.metamodel.procedure.read.ReadStatement;
import io.proleap.cobol.parser.metamodel.procedure.receive.ReceiveStatement;
import io.proleap.cobol.parser.metamodel.procedure.release.ReleaseStatement;
import io.proleap.cobol.parser.metamodel.procedure.returnstmt.ReturnStatement;
import io.proleap.cobol.parser.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.parser.metamodel.procedure.terminate.TerminateStatement;
import io.proleap.cobol.parser.metamodel.procedure.write.WriteStatement;

/**
 * Contains procedures to manipulate the data from data division.
 */
public interface ProcedureDivision extends CobolDivision {

	AcceptStatement addAcceptStatement(AcceptStatementContext ctx);

	AddStatement addAddStatement(AddStatementContext ctx);

	AlterStatement addAlterStatement(AlterStatementContext ctx);

	CallStatement addCallStatement(CallStatementContext ctx);

	CancelStatement addCancelStatement(CancelStatementContext ctx);

	CloseStatement addCloseStatement(CloseStatementContext ctx);

	ComputeStatement addComputeStatement(ComputeStatementContext ctx);

	ContinueStatement addContinueStatement(ContinueStatementContext ctx);

	Declaratives addDeclaratives(ProcedureDeclarativesContext ctx);

	DeleteStatement addDeleteStatement(DeleteStatementContext ctx);

	DisableStatement addDisableStatement(DisableStatementContext ctx);

	DisplayStatement addDisplayStatement(DisplayStatementContext ctx);

	DivideStatement addDivideStatement(DivideStatementContext ctx);

	EnableStatement addEnableStatement(EnableStatementContext ctx);

	EntryStatement addEntryStatement(EntryStatementContext ctx);

	ExitStatement addExitStatement(ExitStatementContext ctx);

	GenerateStatement addGenerateStatement(GenerateStatementContext ctx);

	GobackStatement addGobackStatement(GobackStatementContext ctx);

	GoToStatement addGoToStatement(GoToStatementContext ctx);

	IfStatement addIfStatement(IfStatementContext ctx);

	InitializeStatement addInitializeStatement(InitializeStatementContext ctx);

	InitiateStatement addInitiateStatement(InitiateStatementContext ctx);

	InspectStatement addInspectStatement(InspectStatementContext ctx);

	MoveToStatement addMoveToStatement(MoveToStatementContext ctx);

	Paragraph addParagraph(ParagraphContext ctx);

	ParagraphName addParagraphName(ParagraphNameContext ctx);

	PerformStatement addPerformStatement(PerformStatementContext ctx);

	PurgeStatement addPurgeStatement(PurgeStatementContext ctx);

	ReadStatement addReadStatement(ReadStatementContext ctx);

	ReceiveStatement addReceiveStatement(ReceiveStatementContext ctx);

	ReleaseStatement addReleaseStatement(ReleaseStatementContext ctx);

	ReturnStatement addReturnStatement(ReturnStatementContext ctx);

	StopStatement addStopStatement(StopStatementContext ctx);

	TerminateStatement addTerminateStatement(TerminateStatementContext ctx);

	WriteStatement addWriteStatement(WriteStatementContext ctx);

	Declaratives getDeclaratives();

	Paragraph getParagraph(String name);

	List<Paragraph> getParagraphs();

	List<Statement> getStatements();

}
