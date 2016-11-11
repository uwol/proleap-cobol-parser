/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.FileDescriptionEntryContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.FileDescriptionEntry;

public class FileDescriptionEntryImpl extends DataDivisionBodyImpl implements FileDescriptionEntry {

	protected final FileDescriptionEntryContext ctx;

	protected List<DataDescriptionEntry> dataDescriptionEntries = new ArrayList<DataDescriptionEntry>();

	protected final String name;

	public FileDescriptionEntryImpl(final String name, final ProgramUnit programUnit, final CobolDivision scope,
			final FileDescriptionEntryContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public void addDataDescriptionEntry(final DataDescriptionEntry dataDescriptionEntry) {
		dataDescriptionEntries.add(dataDescriptionEntry);
	}

	@Override
	public List<DataDescriptionEntry> getDataDescriptionEntries() {
		return dataDescriptionEntries;
	}

	@Override
	public String getName() {
		return name;
	}

}
