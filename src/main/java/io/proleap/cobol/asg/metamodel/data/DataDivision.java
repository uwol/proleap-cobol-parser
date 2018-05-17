/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data;

import io.proleap.cobol.CobolParser.CommunicationSectionContext;
import io.proleap.cobol.CobolParser.DataBaseSectionContext;
import io.proleap.cobol.CobolParser.FileSectionContext;
import io.proleap.cobol.CobolParser.LinkageSectionContext;
import io.proleap.cobol.CobolParser.LocalStorageSectionContext;
import io.proleap.cobol.CobolParser.ProgramLibrarySectionContext;
import io.proleap.cobol.CobolParser.ReportSectionContext;
import io.proleap.cobol.CobolParser.ScreenSectionContext;
import io.proleap.cobol.CobolParser.WorkingStorageSectionContext;
import io.proleap.cobol.asg.metamodel.CobolDivision;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.asg.metamodel.data.database.DataBaseSection;
import io.proleap.cobol.asg.metamodel.data.file.FileSection;
import io.proleap.cobol.asg.metamodel.data.linkage.LinkageSection;
import io.proleap.cobol.asg.metamodel.data.localstorage.LocalStorageSection;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ProgramLibrarySection;
import io.proleap.cobol.asg.metamodel.data.report.ReportSection;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenSection;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;

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
