/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntryRename;

//FIXME: add clauses
public class DataDescriptionEntryRenameImpl extends DataDescriptionEntryImpl implements DataDescriptionEntryRename {

	protected final DataDescriptionEntryFormat2Context ctx;

	public DataDescriptionEntryRenameImpl(final String name, final ProgramUnit programUnit,
			final DataDescriptionEntryFormat2Context ctx) {
		super(name, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Type getType() {
		return Type.Rename;
	}

}
