/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.impl;

import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.CommunicationSectionContext;
import io.proleap.cobol.Cobol85Parser.DataBaseSectionContext;
import io.proleap.cobol.Cobol85Parser.DataBaseSectionEntryContext;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.DataDivisionContext;
import io.proleap.cobol.Cobol85Parser.FileDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.FileSectionContext;
import io.proleap.cobol.Cobol85Parser.LibraryDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.LinkageSectionContext;
import io.proleap.cobol.Cobol85Parser.LocalStorageSectionContext;
import io.proleap.cobol.Cobol85Parser.ProgramLibrarySectionContext;
import io.proleap.cobol.Cobol85Parser.ReportContext;
import io.proleap.cobol.Cobol85Parser.ReportSectionContext;
import io.proleap.cobol.Cobol85Parser.ScreenSectionContext;
import io.proleap.cobol.Cobol85Parser.WorkingStorageSectionContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.parser.metamodel.data.communication.impl.CommunicationSectionImpl;
import io.proleap.cobol.parser.metamodel.data.database.DataBaseSection;
import io.proleap.cobol.parser.metamodel.data.database.impl.DataBaseSectionImpl;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.parser.metamodel.data.file.FileSection;
import io.proleap.cobol.parser.metamodel.data.file.impl.FileSectionImpl;
import io.proleap.cobol.parser.metamodel.data.linkage.LinkageSection;
import io.proleap.cobol.parser.metamodel.data.linkage.impl.LinkageSectionImpl;
import io.proleap.cobol.parser.metamodel.data.localstorage.LocalStorageSection;
import io.proleap.cobol.parser.metamodel.data.localstorage.impl.LocalStorageSectionImpl;
import io.proleap.cobol.parser.metamodel.data.programlibrary.ProgramLibrarySection;
import io.proleap.cobol.parser.metamodel.data.programlibrary.impl.ProgramLibrarySectionImpl;
import io.proleap.cobol.parser.metamodel.data.report.ReportSection;
import io.proleap.cobol.parser.metamodel.data.report.impl.ReportSectionImpl;
import io.proleap.cobol.parser.metamodel.data.screen.ScreenSection;
import io.proleap.cobol.parser.metamodel.data.screen.impl.ScreenSectionImpl;
import io.proleap.cobol.parser.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.parser.metamodel.data.workingstorage.impl.WorkingStorageSectionImpl;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionImpl;

public class DataDivisionImpl extends CobolDivisionImpl implements DataDivision {

	protected CommunicationSection communicationSection;

	protected final DataDivisionContext ctx;

	protected DataBaseSection dataBaseSection;

	protected FileSection fileSection;

	protected LinkageSection linkageSection;

	protected LocalStorageSection localStorageSection;

	protected ProgramLibrarySection programLibrarySection;

	protected ReportSection reportSection;

	protected ScreenSection screenSection;

	protected WorkingStorageSection workingStorageSection;

	public DataDivisionImpl(final ProgramUnit programUnit, final DataDivisionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public CommunicationSection addCommunicationSection(final CommunicationSectionContext ctx) {
		CommunicationSection result = (CommunicationSection) getASGElement(ctx);

		if (result == null) {
			result = new CommunicationSectionImpl(programUnit, ctx);

			/*
			 * data descriptions
			 */
			DataDescriptionEntryGroup lastDataDescriptionEntryGroup = null;

			for (final DataDescriptionEntryContext dataDescriptionEntryContext : ctx.dataDescriptionEntry()) {
				final DataDescriptionEntry dataDescriptionEntry = result
						.createDataDescriptionEntry(lastDataDescriptionEntryGroup, dataDescriptionEntryContext);

				if (dataDescriptionEntry instanceof DataDescriptionEntryGroup) {
					lastDataDescriptionEntryGroup = (DataDescriptionEntryGroup) dataDescriptionEntry;
				}
			}

			/*
			 * communication descriptions
			 */
			for (final CommunicationDescriptionEntryContext communicationDescriptionEntryContext : ctx
					.communicationDescriptionEntry()) {
				result.createCommunicationDescriptionEntry(communicationDescriptionEntryContext);
			}

			communicationSection = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DataBaseSection addDataBaseSection(final DataBaseSectionContext ctx) {
		DataBaseSection result = (DataBaseSection) getASGElement(ctx);

		if (result == null) {
			result = new DataBaseSectionImpl(programUnit, ctx);

			for (final DataBaseSectionEntryContext dataBaseSectionEntryContext : ctx.dataBaseSectionEntry()) {
				result.addDataBaseSectionEntry(dataBaseSectionEntryContext);
			}

			dataBaseSection = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public FileSection addFileSection(final FileSectionContext ctx) {
		FileSection result = (FileSection) getASGElement(ctx);

		if (result == null) {
			result = new FileSectionImpl(programUnit, ctx);

			for (final FileDescriptionEntryContext fileDescriptionEntryContext : ctx.fileDescriptionEntry()) {
				result.addFileDescriptionEntry(fileDescriptionEntryContext);
			}

			fileSection = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public LinkageSection addLinkageSection(final LinkageSectionContext ctx) {
		LinkageSection result = (LinkageSection) getASGElement(ctx);

		if (result == null) {
			result = new LinkageSectionImpl(programUnit, ctx);

			/*
			 * data descriptions
			 */
			DataDescriptionEntryGroup lastDataDescriptionEntryGroup = null;

			for (final DataDescriptionEntryContext dataDescriptionEntryContext : ctx.dataDescriptionEntry()) {
				final DataDescriptionEntry dataDescriptionEntry = result
						.createDataDescriptionEntry(lastDataDescriptionEntryGroup, dataDescriptionEntryContext);

				if (dataDescriptionEntry instanceof DataDescriptionEntryGroup) {
					lastDataDescriptionEntryGroup = (DataDescriptionEntryGroup) dataDescriptionEntry;
				}
			}

			linkageSection = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public LocalStorageSection addLocalStorageSection(final LocalStorageSectionContext ctx) {
		LocalStorageSection result = (LocalStorageSection) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx.localName());
			result = new LocalStorageSectionImpl(name, programUnit, ctx);

			/*
			 * data descriptions
			 */
			DataDescriptionEntryGroup lastDataDescriptionEntryGroup = null;

			for (final DataDescriptionEntryContext dataDescriptionEntryContext : ctx.dataDescriptionEntry()) {
				final DataDescriptionEntry dataDescriptionEntry = result
						.createDataDescriptionEntry(lastDataDescriptionEntryGroup, dataDescriptionEntryContext);

				if (dataDescriptionEntry instanceof DataDescriptionEntryGroup) {
					lastDataDescriptionEntryGroup = (DataDescriptionEntryGroup) dataDescriptionEntry;
				}
			}

			localStorageSection = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ProgramLibrarySection addProgramLibrarySection(final ProgramLibrarySectionContext ctx) {
		ProgramLibrarySection result = (ProgramLibrarySection) getASGElement(ctx);

		if (result == null) {
			result = new ProgramLibrarySectionImpl(programUnit, ctx);

			for (final LibraryDescriptionEntryContext libraryDescriptionEntryContext : ctx.libraryDescriptionEntry()) {
				result.createLibraryDescriptionEntry(libraryDescriptionEntryContext);
			}

			programLibrarySection = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ReportSection addReportSection(final ReportSectionContext ctx) {
		ReportSection result = (ReportSection) getASGElement(ctx);

		if (result == null) {
			result = new ReportSectionImpl(programUnit, ctx);

			for (final ReportContext reportContext : ctx.report()) {
				result.addReport(reportContext);
			}

			reportSection = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ScreenSection addScreenSection(final ScreenSectionContext ctx) {
		ScreenSection result = (ScreenSection) getASGElement(ctx);

		if (result == null) {
			result = new ScreenSectionImpl(programUnit, ctx);

			screenSection = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public WorkingStorageSection addWorkingStorageSection(final WorkingStorageSectionContext ctx) {
		WorkingStorageSection result = (WorkingStorageSection) getASGElement(ctx);

		if (result == null) {
			result = new WorkingStorageSectionImpl(programUnit, ctx);

			/*
			 * data descriptions
			 */
			DataDescriptionEntryGroup lastDataDescriptionEntryGroup = null;

			for (final DataDescriptionEntryContext dataDescriptionEntryContext : ctx.dataDescriptionEntry()) {
				final DataDescriptionEntry dataDescriptionEntry = result
						.createDataDescriptionEntry(lastDataDescriptionEntryGroup, dataDescriptionEntryContext);

				if (dataDescriptionEntry instanceof DataDescriptionEntryGroup) {
					lastDataDescriptionEntryGroup = (DataDescriptionEntryGroup) dataDescriptionEntry;
				}
			}

			workingStorageSection = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CommunicationSection getCommunicationSection() {
		return communicationSection;
	}

	@Override
	public DataBaseSection getDataBaseSection() {
		return dataBaseSection;
	}

	@Override
	public FileSection getFileSection() {
		return fileSection;
	}

	@Override
	public LinkageSection getLinkageSection() {
		return linkageSection;
	}

	@Override
	public LocalStorageSection getLocalStorageSection() {
		return localStorageSection;
	}

	@Override
	public ProgramLibrarySection getProgramLibrarySection() {
		return programLibrarySection;
	}

	@Override
	public ReportSection getReportSection() {
		return reportSection;
	}

	@Override
	public ScreenSection getScreenSection() {
		return screenSection;
	}

	@Override
	public WorkingStorageSection getWorkingStorageSection() {
		return workingStorageSection;
	}

}
