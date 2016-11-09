/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.impl;

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat3Context;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry3;

public class DataDescriptionEntry3Impl extends DataDescriptionEntryImpl implements DataDescriptionEntry3 {

	protected final DataDescriptionEntryFormat3Context ctx;

	public DataDescriptionEntry3Impl(final String name, final CopyBook copyBook, final CobolScope superScope,
			final DataDescriptionEntryFormat3Context ctx) {
		super(name, copyBook, superScope, ctx);

		this.ctx = ctx;
	}

}
