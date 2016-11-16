/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.localstorage.impl;

import io.proleap.cobol.Cobol85Parser.LocalStorageSectionContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.impl.DataDescriptionEntryContainerImpl;
import io.proleap.cobol.parser.metamodel.data.localstorage.LocalStorageSection;

public class LocalStorageSectionImpl extends DataDescriptionEntryContainerImpl implements LocalStorageSection {

	protected final LocalStorageSectionContext ctx;

	protected String name;

	public LocalStorageSectionImpl(final String name, final ProgramUnit programUnit,
			final LocalStorageSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
