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
import io.proleap.cobol.Cobol85Parser.MoveToStatementContext;
import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphNameContext;
import io.proleap.cobol.Cobol85Parser.PerformStatementContext;
import io.proleap.cobol.Cobol85Parser.ReleaseStatementContext;
import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.Cobol85Parser.TerminateStatementContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.procedure.accept.AcceptStatement;
import io.proleap.cobol.parser.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.parser.metamodel.procedure.alter.AlterStatement;
import io.proleap.cobol.parser.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.parser.metamodel.procedure.cancel.CancelStatement;
import io.proleap.cobol.parser.metamodel.procedure.close.CloseStatement;
import io.proleap.cobol.parser.metamodel.procedure.compute.ComputeStatement;
import io.proleap.cobol.parser.metamodel.procedure.continuestmt.ContinueStatement;
import io.proleap.cobol.parser.metamodel.procedure.delete.DeleteStatement;
import io.proleap.cobol.parser.metamodel.procedure.disable.DisableStatement;
import io.proleap.cobol.parser.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.parser.metamodel.procedure.divide.DivideStatement;
import io.proleap.cobol.parser.metamodel.procedure.enable.EnableStatement;
import io.proleap.cobol.parser.metamodel.procedure.entry.EntryStatement;
import io.proleap.cobol.parser.metamodel.procedure.move.MoveToStatement;
import io.proleap.cobol.parser.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.parser.metamodel.procedure.release.ReleaseStatement;
import io.proleap.cobol.parser.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.parser.metamodel.procedure.terminate.TerminateStatement;

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

	DeleteStatement addDeleteStatement(DeleteStatementContext ctx);

	DisableStatement addDisableStatement(DisableStatementContext ctx);

	DisplayStatement addDisplayStatement(DisplayStatementContext ctx);

	DivideStatement addDivideStatement(DivideStatementContext ctx);

	EnableStatement addEnableStatement(EnableStatementContext ctx);

	EntryStatement addEntryStatement(EntryStatementContext ctx);

	MoveToStatement addMoveToStatement(MoveToStatementContext ctx);

	Paragraph addParagraph(ParagraphContext ctx);

	ParagraphName addParagraphName(ParagraphNameContext ctx);

	PerformStatement addPerformStatement(PerformStatementContext ctx);

	ReleaseStatement addReleaseStatement(ReleaseStatementContext ctx);

	StopStatement addStopStatement(StopStatementContext ctx);

	TerminateStatement addTerminateStatement(TerminateStatementContext ctx);

	Paragraph getParagraph(String name);

	List<Paragraph> getParagraphs();

	List<Statement> getStatements();

}
