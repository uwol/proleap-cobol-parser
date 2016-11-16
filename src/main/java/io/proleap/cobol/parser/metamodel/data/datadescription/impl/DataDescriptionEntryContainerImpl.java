/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat3Context;
import io.proleap.cobol.Cobol85Parser.DataPictureClauseContext;
import io.proleap.cobol.Cobol85Parser.PictureStringContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntryCondition;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntryContainer;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntryRename;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.util.StringUtils;

public class DataDescriptionEntryContainerImpl extends CobolDivisionElementImpl
		implements DataDescriptionEntryContainer {

	private final static Logger LOG = LogManager.getLogger(DataDescriptionEntryContainerImpl.class);

	protected List<DataDescriptionEntry> dataDescriptionEntries = new ArrayList<DataDescriptionEntry>();

	protected Map<String, DataDescriptionEntry> dataDescriptionEntriesByName = new HashMap<String, DataDescriptionEntry>();

	public DataDescriptionEntryContainerImpl(final ProgramUnit programUnit, final ParseTree ctx) {
		super(programUnit, ctx);
	}

	@Override
	public DataDescriptionEntryCondition addDataDescriptionEntryCondition(
			final DataDescriptionEntryFormat3Context ctx) {
		DataDescriptionEntryCondition result = (DataDescriptionEntryCondition) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new DataDescriptionEntryConditionImpl(name, programUnit, ctx);

			result.setLevelNumber(DataDescriptionEntry.LEVEL_NUMBER_CONDITION);

			registerASGElement(result);

			dataDescriptionEntries.add(result);
			dataDescriptionEntriesByName.put(name, result);
		}

		return result;
	}

	@Override
	public DataDescriptionEntryGroup addDataDescriptionEntryGroup(final DataDescriptionEntryFormat1Context ctx) {
		DataDescriptionEntryGroup result = (DataDescriptionEntryGroup) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new DataDescriptionEntryGroupImpl(name, programUnit, ctx);

			final Integer levelNumber;

			if (ctx.LEVEL_NUMBER_77() != null) {
				levelNumber = DataDescriptionEntry.LEVEL_NUMBER_SCALAR;
			} else if (ctx.INTEGERLITERAL() != null) {
				levelNumber = StringUtils.parseInteger(ctx.INTEGERLITERAL().getText());
			} else {
				levelNumber = null;
			}

			result.setLevelNumber(levelNumber);

			if (!ctx.dataPictureClause().isEmpty()) {
				final DataPictureClauseContext dataPictureClauseContext = ctx.dataPictureClause().get(0);
				final PictureStringContext pictureString = dataPictureClauseContext.pictureString();
				result.setPictureString(pictureString.getText());
			}

			registerASGElement(result);

			dataDescriptionEntries.add(result);
			dataDescriptionEntriesByName.put(name, result);
		}

		return result;
	}

	@Override
	public DataDescriptionEntryRename addDataDescriptionEntryRename(final DataDescriptionEntryFormat2Context ctx) {
		DataDescriptionEntryRename result = (DataDescriptionEntryRename) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new DataDescriptionEntryRenameImpl(name, programUnit, ctx);

			result.setLevelNumber(DataDescriptionEntry.LEVEL_NUMBER_RENAME);

			registerASGElement(result);

			dataDescriptionEntries.add(result);
			dataDescriptionEntriesByName.put(name, result);
		}

		return result;
	}

	@Override
	public DataDescriptionEntry createDataDescriptionEntry(
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
	public List<DataDescriptionEntry> getRootDataDescriptionEntries() {
		final List<DataDescriptionEntry> result = new ArrayList<DataDescriptionEntry>();

		for (final DataDescriptionEntry dataDescriptionEntry : dataDescriptionEntries) {

			if (dataDescriptionEntry.getDataDescriptionEntryGroup() == null) {
				result.add(dataDescriptionEntry);
			}
		}

		return result;
	}

	protected void groupDataDescriptionEntry(final DataDescriptionEntryGroup lastDataDescriptionEntryGroup,
			final DataDescriptionEntry dataDescriptionEntry) {
		final Integer lastLevelNumber = lastDataDescriptionEntryGroup.getLevelNumber();
		final Integer levelNumber = dataDescriptionEntry.getLevelNumber();

		if (DataDescriptionEntry.LEVEL_NUMBER_SCALAR == levelNumber
				|| DataDescriptionEntry.LEVEL_NUMBER_RENAME == levelNumber) {
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

}
