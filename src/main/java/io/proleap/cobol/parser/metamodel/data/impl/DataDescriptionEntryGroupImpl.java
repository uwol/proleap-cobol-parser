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

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntryGroup;

public class DataDescriptionEntryGroupImpl extends DataDescriptionEntryImpl implements DataDescriptionEntryGroup {

	protected final DataDescriptionEntryFormat1Context ctx;

	protected List<DataDescriptionEntryGroup> dataDescriptionEntryGroups = new ArrayList<DataDescriptionEntryGroup>();

	protected DataDescriptionEntryGroup parentDataDescriptionEntryGroup;

	public DataDescriptionEntryGroupImpl(final String name, final ProgramUnit programUnit, final CobolDivision scope,
			final DataDescriptionEntryFormat1Context ctx) {
		super(name, programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addDataDescriptionEntryGroup(final DataDescriptionEntryGroup dataDescriptionEntryGroup) {
		dataDescriptionEntryGroups.add(dataDescriptionEntryGroup);
	}

	@Override
	public List<DataDescriptionEntryGroup> getDataDescriptionEntryGroups() {
		return dataDescriptionEntryGroups;
	}

	@Override
	public DataDescriptionEntryGroup getParentDataDescriptionEntryGroup() {
		return parentDataDescriptionEntryGroup;
	}

	@Override
	public void setParentDataDescriptionEntryGroup(final DataDescriptionEntryGroup dataDescriptionEntry) {
		parentDataDescriptionEntryGroup = dataDescriptionEntry;
	}

}
