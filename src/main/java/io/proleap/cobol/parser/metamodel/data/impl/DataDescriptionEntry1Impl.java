/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.impl;

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry1;

public class DataDescriptionEntry1Impl extends DataDescriptionEntryImpl implements DataDescriptionEntry1 {

	protected final DataDescriptionEntryFormat1Context ctx;

	public DataDescriptionEntry1Impl(final String name, final CopyBook copyBook, final CobolScope superScope,
			final DataDescriptionEntryFormat1Context ctx) {
		super(name, copyBook, superScope, ctx);

		this.ctx = ctx;
	}

}
