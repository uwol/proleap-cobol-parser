/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat3Context;
import io.proleap.cobol.Cobol85Parser.DataDivisionBodyContext;
import io.proleap.cobol.Cobol85Parser.DataDivisionContext;
import io.proleap.cobol.Cobol85Parser.FileDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.FileSectionContext;
import io.proleap.cobol.Cobol85Parser.WorkingStorageSectionContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntryCondition;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntryGroup;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntryRename;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.DataDivisionBody;
import io.proleap.cobol.parser.metamodel.data.FileDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.FileSection;
import io.proleap.cobol.parser.metamodel.data.WorkingStorageSection;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionImpl;
import io.proleap.cobol.parser.util.StringUtils;

public class DataDivisionImpl extends CobolDivisionImpl implements DataDivision {

	private final static Logger LOG = LogManager.getLogger(DataDivisionImpl.class);

	protected final DataDivisionContext ctx;

	protected List<DataDescriptionEntry> dataDescriptionEntries = new ArrayList<DataDescriptionEntry>();

	protected Map<String, DataDescriptionEntry> dataDescriptionEntriesByName = new HashMap<String, DataDescriptionEntry>();

	protected List<DataDescriptionEntryCondition> dataDescriptionEntryConditions = new ArrayList<DataDescriptionEntryCondition>();

	protected Map<String, DataDescriptionEntryCondition> dataDescriptionEntryConditionsByName = new HashMap<String, DataDescriptionEntryCondition>();

	protected List<DataDescriptionEntryGroup> dataDescriptionEntryGroups = new ArrayList<DataDescriptionEntryGroup>();

	protected Map<String, DataDescriptionEntryGroup> dataDescriptionEntryGroupsByName = new HashMap<String, DataDescriptionEntryGroup>();

	protected List<DataDescriptionEntryRename> dataDescriptionEntryRenames = new ArrayList<DataDescriptionEntryRename>();

	protected Map<String, DataDescriptionEntryRename> dataDescriptionEntryRenamesByName = new HashMap<String, DataDescriptionEntryRename>();

	protected DataDivisionBody dataDivisionBody;

	protected List<FileDescriptionEntry> fileDescriptionEntries = new ArrayList<FileDescriptionEntry>();

	protected Map<String, FileDescriptionEntry> fileDescriptionEntriesByName = new HashMap<String, FileDescriptionEntry>();

	public DataDivisionImpl(final ProgramUnit programUnit, final DataDivisionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DataDescriptionEntryCondition addDataDescriptionEntryCondition(
			final DataDescriptionEntryFormat3Context ctx) {
		DataDescriptionEntryCondition result = (DataDescriptionEntryCondition) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new DataDescriptionEntryConditionImpl(name, programUnit, this, ctx);

			result.setLevelNumber(88);

			registerASGElement(result);

			dataDescriptionEntries.add(result);
			dataDescriptionEntriesByName.put(name, result);

			dataDescriptionEntryConditions.add(result);
			dataDescriptionEntryConditionsByName.put(name, result);
		}

		return result;
	}

	@Override
	public DataDescriptionEntryGroup addDataDescriptionEntryGroup(final DataDescriptionEntryFormat1Context ctx) {
		DataDescriptionEntryGroup result = (DataDescriptionEntryGroup) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new DataDescriptionEntryGroupImpl(name, programUnit, this, ctx);

			final Integer levelNumber;

			if (ctx.LEVEL_NUMBER_77() != null) {
				levelNumber = 77;
			} else if (ctx.INTEGERLITERAL() != null) {
				levelNumber = StringUtils.parseInteger(ctx.INTEGERLITERAL().getText());
			} else {
				levelNumber = null;
			}

			result.setLevelNumber(levelNumber);

			registerASGElement(result);

			dataDescriptionEntries.add(result);
			dataDescriptionEntriesByName.put(name, result);

			dataDescriptionEntryGroups.add(result);
			dataDescriptionEntryGroupsByName.put(name, result);
		}

		return result;
	}

	@Override
	public DataDescriptionEntryRename addDataDescriptionEntryRename(final DataDescriptionEntryFormat2Context ctx) {
		DataDescriptionEntryRename result = (DataDescriptionEntryRename) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new DataDescriptionEntryRenameImpl(name, programUnit, this, ctx);

			result.setLevelNumber(66);

			registerASGElement(result);

			dataDescriptionEntries.add(result);
			dataDescriptionEntriesByName.put(name, result);

			dataDescriptionEntryRenames.add(result);
			dataDescriptionEntryRenamesByName.put(name, result);
		}

		return result;
	}

	@Override
	public DataDivisionBody addDataDivisionBody(final DataDivisionBodyContext ctx) {
		DataDivisionBody result = (DataDivisionBody) getASGElement(ctx);

		if (result == null) {
			result = new DataDivisionBodyImpl(programUnit, this, ctx);

			if (ctx.fileSection() != null) {
				final FileSection fileSection = addFileSection(ctx.fileSection());
				result.setFileSection(fileSection);
			}

			if (ctx.workingStorageSection() != null) {
				final WorkingStorageSection workingStorageSection = addWorkingStorageSection(
						ctx.workingStorageSection());
				result.setWorkingStorageSection(workingStorageSection);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public FileDescriptionEntry addFileDescriptionEntry(final FileDescriptionEntryContext ctx) {
		FileDescriptionEntry result = (FileDescriptionEntry) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new FileDescriptionEntryImpl(name, programUnit, this, ctx);

			DataDescriptionEntryGroup lastDataDescriptionEntryGroup = null;

			for (final DataDescriptionEntryContext dataDescriptionEntryContext : ctx.dataDescriptionEntry()) {
				final DataDescriptionEntry dataDescriptionEntry = createDataDescriptionEntry(
						lastDataDescriptionEntryGroup, dataDescriptionEntryContext);

				if (dataDescriptionEntry != null) {
					result.addDataDescriptionEntry(dataDescriptionEntry);
				}

				if (dataDescriptionEntry instanceof DataDescriptionEntryGroup) {
					lastDataDescriptionEntryGroup = (DataDescriptionEntryGroup) dataDescriptionEntry;
				}
			}

			fileDescriptionEntries.add(result);
			fileDescriptionEntriesByName.put(name, result);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public FileSection addFileSection(final FileSectionContext ctx) {
		FileSection result = (FileSection) getASGElement(ctx);

		if (result == null) {
			result = new FileSectionImpl(programUnit, this, ctx);

			for (final FileDescriptionEntryContext fileDescriptionEntryContext : ctx.fileDescriptionEntry()) {
				final FileDescriptionEntry fileDescriptionEntry = addFileDescriptionEntry(fileDescriptionEntryContext);
				result.addFileDescriptionEntry(fileDescriptionEntry);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public WorkingStorageSection addWorkingStorageSection(final WorkingStorageSectionContext ctx) {
		WorkingStorageSection result = (WorkingStorageSection) getASGElement(ctx);

		if (result == null) {
			result = new WorkingStorageSectionImpl(programUnit, this, ctx);

			DataDescriptionEntryGroup lastDataDescriptionEntryGroup = null;

			for (final DataDescriptionEntryContext dataDescriptionEntryContext : ctx.dataDescriptionEntry()) {
				final DataDescriptionEntry dataDescriptionEntry = createDataDescriptionEntry(
						lastDataDescriptionEntryGroup, dataDescriptionEntryContext);

				if (dataDescriptionEntry != null) {
					result.addDataDescriptionEntry(dataDescriptionEntry);
				}

				if (dataDescriptionEntry instanceof DataDescriptionEntryGroup) {
					lastDataDescriptionEntryGroup = (DataDescriptionEntryGroup) dataDescriptionEntry;
				}
			}

			registerASGElement(result);
		}

		return result;
	}

	protected DataDescriptionEntry createDataDescriptionEntry(
			final DataDescriptionEntryGroup lastDataDescriptionEntryGroup,
			final DataDescriptionEntryContext dataDescriptionEntryContext) {
		final DataDescriptionEntry result;

		if (dataDescriptionEntryContext.dataDescriptionEntryFormat1() != null) {
			result = addDataDescriptionEntryGroup(dataDescriptionEntryContext.dataDescriptionEntryFormat1());
		} else if (dataDescriptionEntryContext.dataDescriptionEntryFormat2() != null) {
			result = addDataDescriptionEntryRename(dataDescriptionEntryContext.dataDescriptionEntryFormat2());
		} else if (dataDescriptionEntryContext.dataDescriptionEntryFormat3() != null) {
			result = addDataDescriptionEntryCondition(dataDescriptionEntryContext.dataDescriptionEntryFormat3());
		} else {
			LOG.warn("unknown data description entry {}", dataDescriptionEntryContext);
			result = null;
		}

		if (lastDataDescriptionEntryGroup != null && result != null) {
			groupDataDescriptionEntry(lastDataDescriptionEntryGroup, result);
		}

		return result;
	}

	@Override
	public List<DataDescriptionEntry> getDataDescriptionEntries() {
		return dataDescriptionEntries;
	}

	@Override
	public DataDescriptionEntry getDataDescriptionEntry(final String name) {
		return dataDescriptionEntriesByName.get(name);
	}

	@Override
	public DataDescriptionEntryCondition getDataDescriptionEntryCondition(final String name) {
		return dataDescriptionEntryConditionsByName.get(name);
	}

	@Override
	public List<DataDescriptionEntryCondition> getDataDescriptionEntryConditions() {
		return dataDescriptionEntryConditions;
	}

	@Override
	public DataDescriptionEntryGroup getDataDescriptionEntryGroup(final String name) {
		return dataDescriptionEntryGroupsByName.get(name);
	}

	@Override
	public List<DataDescriptionEntryGroup> getDataDescriptionEntryGroups() {
		return dataDescriptionEntryGroups;
	}

	@Override
	public DataDescriptionEntryRename getDataDescriptionEntryRename(final String name) {
		return dataDescriptionEntryRenamesByName.get(name);
	}

	@Override
	public List<DataDescriptionEntryRename> getDataDescriptionEntryRenames() {
		return dataDescriptionEntryRenames;
	}

	@Override
	public DataDivisionBody getDataDivisionBody() {
		return dataDivisionBody;
	}

	@Override
	public List<FileDescriptionEntry> getFileDescriptionEntries() {
		return fileDescriptionEntries;
	}

	@Override
	public FileDescriptionEntry getFileDescriptionEntry(final String name) {
		return fileDescriptionEntriesByName.get(name);
	}

	protected void groupDataDescriptionEntry(final DataDescriptionEntryGroup lastDataDescriptionEntryGroup,
			final DataDescriptionEntry dataDescriptionEntry) {
		final Integer lastLevelNumber = lastDataDescriptionEntryGroup.getLevelNumber();
		final Integer levelNumber = dataDescriptionEntry.getLevelNumber();

		if (LEVEL_NUMBER_SCALAR == levelNumber || LEVEL_NUMBER_RENAME == levelNumber) {
		} else if (levelNumber > lastLevelNumber) {
			lastDataDescriptionEntryGroup.addDataDescriptionEntry(dataDescriptionEntry);
			dataDescriptionEntry.setDataDescriptionEntryGroup(lastDataDescriptionEntryGroup);
		} else {
			final DataDescriptionEntryGroup lastSuperDataDescriptionEntryGroup = lastDataDescriptionEntryGroup
					.getDataDescriptionEntryGroup();

			if (lastSuperDataDescriptionEntryGroup != null) {
				groupDataDescriptionEntry(lastSuperDataDescriptionEntryGroup, dataDescriptionEntry);
			}
		}
	}

	@Override
	public void setDataDivisionBody(final DataDivisionBody dataDivisionBody) {
		this.dataDivisionBody = dataDivisionBody;
	}
}
