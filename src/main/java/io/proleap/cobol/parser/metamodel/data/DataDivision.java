/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data;

import io.proleap.cobol.Cobol85Parser.CommunicationSectionContext;
import io.proleap.cobol.Cobol85Parser.DataBaseSectionContext;
import io.proleap.cobol.Cobol85Parser.FileSectionContext;
import io.proleap.cobol.Cobol85Parser.LinkageSectionContext;
import io.proleap.cobol.Cobol85Parser.LocalStorageSectionContext;
import io.proleap.cobol.Cobol85Parser.ProgramLibrarySectionContext;
import io.proleap.cobol.Cobol85Parser.ReportSectionContext;
import io.proleap.cobol.Cobol85Parser.ScreenSectionContext;
import io.proleap.cobol.Cobol85Parser.WorkingStorageSectionContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.parser.metamodel.data.database.DataBaseSection;
import io.proleap.cobol.parser.metamodel.data.file.FileSection;
import io.proleap.cobol.parser.metamodel.data.linkage.LinkageSection;
import io.proleap.cobol.parser.metamodel.data.localstorage.LocalStorageSection;
import io.proleap.cobol.parser.metamodel.data.programlibrary.ProgramLibrarySection;
import io.proleap.cobol.parser.metamodel.data.report.ReportSection;
import io.proleap.cobol.parser.metamodel.data.screen.ScreenSection;
import io.proleap.cobol.parser.metamodel.data.workingstorage.WorkingStorageSection;

/**
 * Defines variables used in the program.
 */
public interface DataDivision extends CobolDivision {

	CommunicationSection addCommunicationSection(CommunicationSectionContext ctx);

	DataBaseSection addDataBaseSection(DataBaseSectionContext ctx);

	FileSection addFileSection(FileSectionContext ctx);

	LinkageSection addLinkageSection(LinkageSectionContext ctx);

	LocalStorageSection addLocalStorageSection(LocalStorageSectionContext ctx);

	ProgramLibrarySection addProgramLibrarySection(ProgramLibrarySectionContext ctx);

	ReportSection addReportSection(ReportSectionContext ctx);

	ScreenSection addScreenSection(ScreenSectionContext ctx);

	WorkingStorageSection addWorkingStorageSection(WorkingStorageSectionContext ctx);

	CommunicationSection getCommunicationSection();

	DataBaseSection getDataBaseSection();

	FileSection getFileSection();

	LinkageSection getLinkageSection();

	LocalStorageSection getLocalStorageSection();

	ProgramLibrarySection getProgramLibrarySection();

	ReportSection getReportSection();

	ScreenSection getScreenSection();

	WorkingStorageSection getWorkingStorageSection();

}
