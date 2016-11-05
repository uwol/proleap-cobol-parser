/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel;

import io.proleap.cobol.Cobol85Parser.DisplayStatementContext;
import io.proleap.cobol.Cobol85Parser.IdentificationDivisionContext;
import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphNameContext;
import io.proleap.cobol.Cobol85Parser.ProcedureDivisionContext;
import io.proleap.cobol.Cobol85Parser.ProgramIdParagraphContext;
import io.proleap.cobol.Cobol85Parser.StopStatementContext;

public interface CobolScope extends CobolScopedElement {

	DisplayStatement addDisplayStatement(DisplayStatementContext ctx);

	IdentificationDivision addIdentificationDivision(IdentificationDivisionContext ctx);

	Paragraph addParagraph(ParagraphContext ctx);

	ParagraphName addParagraphName(ParagraphNameContext ctx);

	ProcedureDivision addProcedureDivision(ProcedureDivisionContext ctx);

	ProgramIdParagraph addProgramIdParagraph(ProgramIdParagraphContext ctx);

	StopStatement addStopStatement(StopStatementContext ctx);

	Paragraph getParagraph(String name);
}
