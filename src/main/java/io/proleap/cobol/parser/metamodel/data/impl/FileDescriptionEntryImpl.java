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

import io.proleap.cobol.Cobol85Parser.FileDescriptionEntryContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.FileDescriptionEntry;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class FileDescriptionEntryImpl extends CobolDivisionElementImpl implements FileDescriptionEntry {

	protected final FileDescriptionEntryContext ctx;

	protected List<DataDescriptionEntry> dataDescriptionEntries = new ArrayList<DataDescriptionEntry>();

	protected Map<String, DataDescriptionEntry> dataDescriptionEntriesByName = new HashMap<String, DataDescriptionEntry>();

	protected final String name;

	public FileDescriptionEntryImpl(final String name, final ProgramUnit programUnit,
			final FileDescriptionEntryContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public void addDataDescriptionEntry(final DataDescriptionEntry dataDescriptionEntry) {
		final String name = dataDescriptionEntry.getName();

		dataDescriptionEntries.add(dataDescriptionEntry);
		dataDescriptionEntriesByName.put(name, dataDescriptionEntry);
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
	public String getName() {
		return name;
	}

}
