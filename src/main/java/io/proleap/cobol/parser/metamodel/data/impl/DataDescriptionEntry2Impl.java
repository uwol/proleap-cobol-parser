/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.impl;

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry2;

public class DataDescriptionEntry2Impl extends DataDescriptionEntryImpl implements DataDescriptionEntry2 {

	protected final DataDescriptionEntryFormat2Context ctx;

	public DataDescriptionEntry2Impl(final String name, final ProgramUnit programUnit, final CobolDivision scope,
			final DataDescriptionEntryFormat2Context ctx) {
		super(name, programUnit, scope, ctx);

		this.ctx = ctx;
	}

}
