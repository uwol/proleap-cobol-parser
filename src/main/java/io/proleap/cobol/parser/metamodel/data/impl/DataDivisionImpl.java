/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.impl;

import io.proleap.cobol.Cobol85Parser.DataBaseSectionContext;
import io.proleap.cobol.Cobol85Parser.DataBaseSectionEntryContext;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.DataDivisionContext;
import io.proleap.cobol.Cobol85Parser.FileDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.FileSectionContext;
import io.proleap.cobol.Cobol85Parser.LinkageSectionContext;
import io.proleap.cobol.Cobol85Parser.WorkingStorageSectionContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntryGroup;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.database.DataBaseSection;
import io.proleap.cobol.parser.metamodel.data.database.impl.DataBaseSectionImpl;
import io.proleap.cobol.parser.metamodel.data.file.FileSection;
import io.proleap.cobol.parser.metamodel.data.file.impl.FileSectionImpl;
import io.proleap.cobol.parser.metamodel.data.linkage.LinkageSection;
import io.proleap.cobol.parser.metamodel.data.linkage.impl.LinkageSectionImpl;
import io.proleap.cobol.parser.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.parser.metamodel.data.workingstorage.impl.WorkingStorageSectionImpl;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionImpl;

//FIXME: add sections
public class DataDivisionImpl extends CobolDivisionImpl implements DataDivision {

	protected final DataDivisionContext ctx;

	protected DataBaseSection dataBaseSection;

	protected FileSection fileSection;

	protected LinkageSection linkageSection;

	protected WorkingStorageSection workingStorageSection;

	public DataDivisionImpl(final ProgramUnit programUnit, final DataDivisionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
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
	public WorkingStorageSection addWorkingStorageSection(final WorkingStorageSectionContext ctx) {
		WorkingStorageSection result = (WorkingStorageSection) getASGElement(ctx);

		if (result == null) {
			result = new WorkingStorageSectionImpl(programUnit, ctx);

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
	public WorkingStorageSection getWorkingStorageSection() {
		return workingStorageSection;
	}

}
