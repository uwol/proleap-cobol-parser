/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.AuthorParagraphContext;
import io.proleap.cobol.Cobol85Parser.ConfigurationSectionContext;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat3Context;
import io.proleap.cobol.Cobol85Parser.DataDivisionBodyContext;
import io.proleap.cobol.Cobol85Parser.DataDivisionContext;
import io.proleap.cobol.Cobol85Parser.DateCompiledParagraphContext;
import io.proleap.cobol.Cobol85Parser.DateWrittenParagraphContext;
import io.proleap.cobol.Cobol85Parser.DisplayStatementContext;
import io.proleap.cobol.Cobol85Parser.EnvironmentDivisionContext;
import io.proleap.cobol.Cobol85Parser.IdentificationDivisionContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.InstallationParagraphContext;
import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementSendingAreaContext;
import io.proleap.cobol.Cobol85Parser.ObjectComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphNameContext;
import io.proleap.cobol.Cobol85Parser.PerformProcedureStatementContext;
import io.proleap.cobol.Cobol85Parser.PerformStatementContext;
import io.proleap.cobol.Cobol85Parser.ProcedureDivisionContext;
import io.proleap.cobol.Cobol85Parser.ProcedureNameContext;
import io.proleap.cobol.Cobol85Parser.ProgramIdParagraphContext;
import io.proleap.cobol.Cobol85Parser.RemarksParagraphContext;
import io.proleap.cobol.Cobol85Parser.SecurityParagraphContext;
import io.proleap.cobol.Cobol85Parser.SourceComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.DataDivisionBody;
import io.proleap.cobol.parser.metamodel.environment.ConfigurationSection;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.ObjectComputerParagraph;
import io.proleap.cobol.parser.metamodel.environment.SourceComputerParagraph;
import io.proleap.cobol.parser.metamodel.identification.AuthorParagraph;
import io.proleap.cobol.parser.metamodel.identification.DateCompiledParagraph;
import io.proleap.cobol.parser.metamodel.identification.DateWrittenParagraph;
import io.proleap.cobol.parser.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.parser.metamodel.identification.InstallationParagraph;
import io.proleap.cobol.parser.metamodel.identification.ProgramIdParagraph;
import io.proleap.cobol.parser.metamodel.identification.RemarksParagraph;
import io.proleap.cobol.parser.metamodel.identification.SecurityParagraph;
import io.proleap.cobol.parser.metamodel.procedure.DisplayStatement;
import io.proleap.cobol.parser.metamodel.procedure.MoveToStatement;
import io.proleap.cobol.parser.metamodel.procedure.PerformProcedureStatement;
import io.proleap.cobol.parser.metamodel.procedure.PerformStatement;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.StopStatement;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface CobolScope extends CobolScopedElement {

	AuthorParagraph addAuthorParagraph(AuthorParagraphContext ctx);

	Call addCall(IdentifierContext ctx);

	Call addCall(ProcedureNameContext ctx);

	ConfigurationSection addConfigurationSection(ConfigurationSectionContext ctx);

	DataDescriptionEntry addDataDescriptionEntry(DataDescriptionEntryFormat1Context ctx);

	DataDescriptionEntry addDataDescriptionEntry(DataDescriptionEntryFormat2Context ctx);

	DataDescriptionEntry addDataDescriptionEntry(DataDescriptionEntryFormat3Context ctx);

	DataDivision addDataDivision(DataDivisionContext ctx);

	DataDivisionBody addDataDivisionBody(DataDivisionBodyContext ctx);

	DateCompiledParagraph addDateCompiledParagraph(DateCompiledParagraphContext ctx);

	DateWrittenParagraph addDateWrittenParagraph(DateWrittenParagraphContext ctx);

	DisplayStatement addDisplayStatement(DisplayStatementContext ctx);

	EnvironmentDivision addEnvironmentDivision(EnvironmentDivisionContext ctx);

	IdentificationDivision addIdentificationDivision(IdentificationDivisionContext ctx);

	InstallationParagraph addInstallationParagraph(InstallationParagraphContext ctx);

	Literal addLiteral(LiteralContext ctx);

	MoveToStatement addMoveToStatement(MoveToStatementContext ctx);

	ObjectComputerParagraph addObjectComputerParagraph(ObjectComputerParagraphContext ctx);

	Paragraph addParagraph(ParagraphContext ctx);

	ParagraphName addParagraphName(ParagraphNameContext ctx);

	PerformProcedureStatement addPerformProcedureStatement(PerformProcedureStatementContext ctx);

	PerformStatement addPerformStatement(PerformStatementContext ctx);

	ProcedureDivision addProcedureDivision(ProcedureDivisionContext ctx);

	ProgramIdParagraph addProgramIdParagraph(ProgramIdParagraphContext ctx);

	RemarksParagraph addRemarksParagraph(RemarksParagraphContext ctx);

	SecurityParagraph addSecurityParagraph(SecurityParagraphContext ctx);

	SourceComputerParagraph addSourceComputerParagraph(SourceComputerParagraphContext ctx);

	StopStatement addStopStatement(StopStatementContext ctx);

	ValueStmt addValueStmt(MoveToStatementSendingAreaContext ctx);

	DataDescriptionEntry getDataDescriptionEntry(String name);

	Paragraph getParagraph(String name);

	List<CobolScopedElement> getScopedElements();

	List<CobolScopedElement> getScopedElementsInHierarchy(String name);

	List<CobolScopedElement> getScopedElementsInScope(String name);

	List<CobolScope> getSubScopes();

	void storeScopedElement(CobolScopedElement scopedElement);
}
