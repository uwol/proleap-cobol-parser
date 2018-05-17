/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.impl;

import io.proleap.cobol.CobolParser.CommunicationDescriptionEntryContext;
import io.proleap.cobol.CobolParser.CommunicationSectionContext;
import io.proleap.cobol.CobolParser.DataBaseSectionContext;
import io.proleap.cobol.CobolParser.DataBaseSectionEntryContext;
import io.proleap.cobol.CobolParser.DataDescriptionEntryContext;
import io.proleap.cobol.CobolParser.DataDivisionContext;
import io.proleap.cobol.CobolParser.FileDescriptionEntryContext;
import io.proleap.cobol.CobolParser.FileSectionContext;
import io.proleap.cobol.CobolParser.LibraryDescriptionEntryContext;
import io.proleap.cobol.CobolParser.LinkageSectionContext;
import io.proleap.cobol.CobolParser.LocalStorageSectionContext;
import io.proleap.cobol.CobolParser.ProgramLibrarySectionContext;
import io.proleap.cobol.CobolParser.ReportDescriptionContext;
import io.proleap.cobol.CobolParser.ReportSectionContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionEntryContext;
import io.proleap.cobol.CobolParser.ScreenSectionContext;
import io.proleap.cobol.CobolParser.WorkingStorageSectionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.asg.metamodel.data.communication.impl.CommunicationSectionImpl;
import io.proleap.cobol.asg.metamodel.data.database.DataBaseSection;
import io.proleap.cobol.asg.metamodel.data.database.impl.DataBaseSectionImpl;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.file.FileSection;
import io.proleap.cobol.asg.metamodel.data.file.impl.FileSectionImpl;
import io.proleap.cobol.asg.metamodel.data.linkage.LinkageSection;
import io.proleap.cobol.asg.metamodel.data.linkage.impl.LinkageSectionImpl;
import io.proleap.cobol.asg.metamodel.data.localstorage.LocalStorageSection;
import io.proleap.cobol.asg.metamodel.data.localstorage.impl.LocalStorageSectionImpl;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ProgramLibrarySection;
import io.proleap.cobol.asg.metamodel.data.programlibrary.impl.ProgramLibrarySectionImpl;
import io.proleap.cobol.asg.metamodel.data.report.ReportSection;
import io.proleap.cobol.asg.metamodel.data.report.impl.ReportSectionImpl;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenSection;
import io.proleap.cobol.asg.metamodel.data.screen.impl.ScreenSectionImpl;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.data.workingstorage.impl.WorkingStorageSectionImpl;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionImpl;

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
			communicationSection = result;

			/*
			 * data descriptions
			 */
			DataDescriptionEntryGroup currentDataDescriptionEntryGroup = null;

			for (final DataDescriptionEntryContext dataDescriptionEntryContext : ctx.dataDescriptionEntry()) {
				final DataDescriptionEntry dataDescriptionEntry = result
						.createDataDescriptionEntry(currentDataDescriptionEntryGroup, dataDescriptionEntryContext);

				if (dataDescriptionEntry instanceof DataDescriptionEntryGroup) {
					currentDataDescriptionEntryGroup = (DataDescriptionEntryGroup) dataDescriptionEntry;
				}
			}

			/*
			 * communication descriptions
			 */
			for (final CommunicationDescriptionEntryContext communicationDescriptionEntryContext : ctx
					.communicationDescriptionEntry()) {
				result.createCommunicationDescriptionEntry(communicationDescriptionEntryContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DataBaseSection addDataBaseSection(final DataBaseSectionContext ctx) {
		DataBaseSection result = (DataBaseSection) getASGElement(ctx);

		if (result == null) {
			result = new DataBaseSectionImpl(programUnit, ctx);
			dataBaseSection = result;

			for (final DataBaseSectionEntryContext dataBaseSectionEntryContext : ctx.dataBaseSectionEntry()) {
				result.addDataBaseSectionEntry(dataBaseSectionEntryContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public FileSection addFileSection(final FileSectionContext ctx) {
		FileSection result = (FileSection) getASGElement(ctx);

		if (result == null) {
			result = new FileSectionImpl(programUnit, ctx);
			fileSection = result;

			for (final FileDescriptionEntryContext fileDescriptionEntryContext : ctx.fileDescriptionEntry()) {
				result.addFileDescriptionEntry(fileDescriptionEntryContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public LinkageSection addLinkageSection(final LinkageSectionContext ctx) {
		LinkageSection result = (LinkageSection) getASGElement(ctx);

		if (result == null) {
			result = new LinkageSectionImpl(programUnit, ctx);
			linkageSection = result;

			/*
			 * data descriptions
			 */
			DataDescriptionEntryGroup currentDataDescriptionEntryGroup = null;

			for (final DataDescriptionEntryContext dataDescriptionEntryContext : ctx.dataDescriptionEntry()) {
				final DataDescriptionEntry dataDescriptionEntry = result
						.createDataDescriptionEntry(currentDataDescriptionEntryGroup, dataDescriptionEntryContext);

				if (dataDescriptionEntry instanceof DataDescriptionEntryGroup) {
					currentDataDescriptionEntryGroup = (DataDescriptionEntryGroup) dataDescriptionEntry;
				}
			}

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
			localStorageSection = result;

			/*
			 * data descriptions
			 */
			DataDescriptionEntryGroup currentDataDescriptionEntryGroup = null;

			for (final DataDescriptionEntryContext dataDescriptionEntryContext : ctx.dataDescriptionEntry()) {
				final DataDescriptionEntry dataDescriptionEntry = result
						.createDataDescriptionEntry(currentDataDescriptionEntryGroup, dataDescriptionEntryContext);

				if (dataDescriptionEntry instanceof DataDescriptionEntryGroup) {
					currentDataDescriptionEntryGroup = (DataDescriptionEntryGroup) dataDescriptionEntry;
				}
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ProgramLibrarySection addProgramLibrarySection(final ProgramLibrarySectionContext ctx) {
		ProgramLibrarySection result = (ProgramLibrarySection) getASGElement(ctx);

		if (result == null) {
			result = new ProgramLibrarySectionImpl(programUnit, ctx);
			programLibrarySection = result;

			for (final LibraryDescriptionEntryContext libraryDescriptionEntryContext : ctx.libraryDescriptionEntry()) {
				result.createLibraryDescriptionEntry(libraryDescriptionEntryContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ReportSection addReportSection(final ReportSectionContext ctx) {
		ReportSection result = (ReportSection) getASGElement(ctx);

		if (result == null) {
			result = new ReportSectionImpl(programUnit, ctx);
			reportSection = result;

			for (final ReportDescriptionContext reportContext : ctx.reportDescription()) {
				result.addReportDescription(reportContext);
			}

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

			/*
			 * screen description entry
			 */
			ScreenDescriptionEntry currentScreenDescriptionEntry = null;

			for (final ScreenDescriptionEntryContext screenDescriptionEntryContext : ctx.screenDescriptionEntry()) {
				final ScreenDescriptionEntry screenDescriptionEntry = createScreenDescriptionEntry(
						currentScreenDescriptionEntry, screenDescriptionEntryContext);
				currentScreenDescriptionEntry = screenDescriptionEntry;
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public WorkingStorageSection addWorkingStorageSection(final WorkingStorageSectionContext ctx) {
		WorkingStorageSection result = (WorkingStorageSection) getASGElement(ctx);

		if (result == null) {
			result = new WorkingStorageSectionImpl(programUnit, ctx);
			workingStorageSection = result;

			/*
			 * data descriptions
			 */
			DataDescriptionEntryGroup currentDataDescriptionEntryGroup = null;

			for (final DataDescriptionEntryContext dataDescriptionEntryContext : ctx.dataDescriptionEntry()) {
				final DataDescriptionEntry dataDescriptionEntry = result
						.createDataDescriptionEntry(currentDataDescriptionEntryGroup, dataDescriptionEntryContext);

				if (dataDescriptionEntry instanceof DataDescriptionEntryGroup) {
					currentDataDescriptionEntryGroup = (DataDescriptionEntryGroup) dataDescriptionEntry;
				}
			}

			registerASGElement(result);
		}

		return result;
	}

	protected ScreenDescriptionEntry createScreenDescriptionEntry(
			final ScreenDescriptionEntry currentScreenDescriptionEntry,
			final ScreenDescriptionEntryContext screenDescriptionEntryContext) {
		final ScreenDescriptionEntry result = screenSection.addScreenDescriptionEntry(screenDescriptionEntryContext);

		if (currentScreenDescriptionEntry != null && result != null) {
			groupScreenDescriptionEntry(currentScreenDescriptionEntry, result);
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

	protected void groupScreenDescriptionEntry(final ScreenDescriptionEntry currentScreenDescriptionEntry,
			final ScreenDescriptionEntry screenDescriptionEntry) {
		final Integer currentLevelNumber = currentScreenDescriptionEntry.getLevelNumber();
		final Integer levelNumber = screenDescriptionEntry.getLevelNumber();

		if (levelNumber > currentLevelNumber) {
			currentScreenDescriptionEntry.addScreenDescriptionEntry(screenDescriptionEntry);
			screenDescriptionEntry.setParentScreenDescriptionEntry(currentScreenDescriptionEntry);
		} else {
			final ScreenDescriptionEntry currentParentScreenDescriptionEntry = currentScreenDescriptionEntry
					.getParentScreenDescriptionEntry();

			if (currentParentScreenDescriptionEntry != null) {
				groupScreenDescriptionEntry(currentParentScreenDescriptionEntry, screenDescriptionEntry);
			}
		}
	}
}
