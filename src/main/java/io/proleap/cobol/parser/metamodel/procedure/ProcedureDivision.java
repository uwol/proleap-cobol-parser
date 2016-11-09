/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure;

import io.proleap.cobol.Cobol85Parser.DisplayStatementContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementSendingAreaContext;
import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphNameContext;
import io.proleap.cobol.Cobol85Parser.PerformProcedureStatementContext;
import io.proleap.cobol.Cobol85Parser.PerformStatementContext;
import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface ProcedureDivision extends CobolDivision {

	DisplayStatement addDisplayStatement(DisplayStatementContext ctx);

	MoveToStatement addMoveToStatement(MoveToStatementContext ctx);

	Paragraph addParagraph(ParagraphContext ctx);

	ParagraphName addParagraphName(ParagraphNameContext ctx);

	PerformProcedureStatement addPerformProcedureStatement(PerformProcedureStatementContext ctx);

	PerformStatement addPerformStatement(PerformStatementContext ctx);

	StopStatement addStopStatement(StopStatementContext ctx);

	ValueStmt addValueStmt(MoveToStatementSendingAreaContext ctx);

	Paragraph getParagraph(String name);

}
