/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.AssignClauseContext;
import io.proleap.cobol.Cobol85Parser.CharacterSetClauseContext;
import io.proleap.cobol.Cobol85Parser.CollatingSequenceClauseContext;
import io.proleap.cobol.Cobol85Parser.ConfigurationSectionContext;
import io.proleap.cobol.Cobol85Parser.DiskSizeClauseContext;
import io.proleap.cobol.Cobol85Parser.FileControlEntryContext;
import io.proleap.cobol.Cobol85Parser.FileControlParagraphContext;
import io.proleap.cobol.Cobol85Parser.InputOutputSectionContext;
import io.proleap.cobol.Cobol85Parser.IoControlParagraphContext;
import io.proleap.cobol.Cobol85Parser.MemorySizeClauseContext;
import io.proleap.cobol.Cobol85Parser.ObjectComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.OrganizationClauseContext;
import io.proleap.cobol.Cobol85Parser.PaddingCharacterClauseContext;
import io.proleap.cobol.Cobol85Parser.ReserveClauseContext;
import io.proleap.cobol.Cobol85Parser.SegmentLimitClauseContext;
import io.proleap.cobol.Cobol85Parser.SelectClauseContext;
import io.proleap.cobol.Cobol85Parser.SourceComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.SpecialNamesParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;

public interface EnvironmentDivision extends CobolDivision {

	AssignClause addAssignClause(AssignClauseContext ctx);

	CharacterSetClause addCharacterSetClause(CharacterSetClauseContext ctx);

	CollatingSequenceClause addCollatingSequenceClause(CollatingSequenceClauseContext ctx);

	ConfigurationSection addConfigurationSection(ConfigurationSectionContext ctx);

	DiskSizeClause addDiskSizeClause(DiskSizeClauseContext ctx);

	FileControlEntry addFileControlEntry(FileControlEntryContext ctx);

	FileControlParagraph addFileControlParagraph(FileControlParagraphContext ctx);

	InputOutputSection addInputOutputSection(InputOutputSectionContext ctx);

	IoControlParagraph addIoControlParagraph(IoControlParagraphContext ctx);

	MemorySizeClause addMemorySizeClause(MemorySizeClauseContext ctx);

	ObjectComputerParagraph addObjectComputerParagraph(ObjectComputerParagraphContext ctx);

	OrganizationClause addOrganizationClause(OrganizationClauseContext ctx);

	PaddingCharacterClause addPaddingCharacterClause(PaddingCharacterClauseContext ctx);

	ReserveClause addReserveClause(ReserveClauseContext ctx);

	SegmentLimitClause addSegmentLimitClause(SegmentLimitClauseContext ctx);

	SelectClause addSelectClause(SelectClauseContext ctx);

	SourceComputerParagraph addSourceComputerParagraph(SourceComputerParagraphContext ctx);

	SpecialNamesParagraph addSpecialNamesParagraph(SpecialNamesParagraphContext ctx);

	ConfigurationSection getConfigurationSection();

	List<FileControlEntry> getFileControlEntries();

	FileControlEntry getFileControlEntry(String name);

	InputOutputSection getInputOutputSection();

	SpecialNamesParagraph getSpecialNamesParagraph();

}
