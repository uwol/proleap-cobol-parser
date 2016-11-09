/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.impl;

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry2;

public class DataDescriptionEntry2Impl extends DataDescriptionEntryImpl implements DataDescriptionEntry2 {

	protected final DataDescriptionEntryFormat2Context ctx;

	public DataDescriptionEntry2Impl(final String name, final CopyBook copyBook, final CobolScope superScope,
			final DataDescriptionEntryFormat2Context ctx) {
		super(name, copyBook, superScope, ctx);

		this.ctx = ctx;
	}

}
