/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntriesSymbolTableEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;

public class DataDescriptionEntriesSymbolTableEntryImpl implements DataDescriptionEntriesSymbolTableEntry {

	protected final static Logger LOG = LogManager.getLogger(DataDescriptionEntriesSymbolTableEntryImpl.class);

	protected List<DataDescriptionEntry> dataDescriptionEntries = new ArrayList<DataDescriptionEntry>();

	@Override
	public void addDataDescriptionEntry(final DataDescriptionEntry dataDescriptionEntry) {
		if (!dataDescriptionEntries.isEmpty()) {
			LOG.debug("multiple declarations of data description entry {}", dataDescriptionEntry);
		}

		dataDescriptionEntries.add(dataDescriptionEntry);
	}

	@Override
	public List<DataDescriptionEntry> getDataDescriptionEntries() {
		return dataDescriptionEntries;
	}

	@Override
	public DataDescriptionEntry getDataDescriptionEntry() {
		return dataDescriptionEntries.isEmpty() ? null : dataDescriptionEntries.get(0);
	}
}
