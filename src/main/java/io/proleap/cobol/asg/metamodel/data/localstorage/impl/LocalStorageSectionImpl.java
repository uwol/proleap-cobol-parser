/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.localstorage.impl;

import io.proleap.cobol.CobolParser.LocalStorageSectionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.impl.DataDescriptionEntryContainerImpl;
import io.proleap.cobol.asg.metamodel.data.localstorage.LocalStorageSection;

public class LocalStorageSectionImpl extends DataDescriptionEntryContainerImpl implements LocalStorageSection {

	protected final DataDescriptionEntryContainerType containerType = DataDescriptionEntryContainerType.LOCAL_STORAGE_SECTION;

	protected final LocalStorageSectionContext ctx;

	protected String name;

	public LocalStorageSectionImpl(final String name, final ProgramUnit programUnit,
			final LocalStorageSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public DataDescriptionEntryContainerType getContainerType() {
		return containerType;
	}

	@Override
	public String getName() {
		return name;
	}

}
