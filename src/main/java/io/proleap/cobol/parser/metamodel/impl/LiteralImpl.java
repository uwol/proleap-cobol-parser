/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Literal;

public class LiteralImpl extends CobolScopedElementImpl implements Literal {

	protected final LiteralContext ctx;

	protected final String value;

	public LiteralImpl(final String value, final CopyBook copyBook, final CobolScope superScope,
			final LiteralContext ctx) {
		super(copyBook, superScope, ctx);

		this.ctx = ctx;
		this.value = value;
	}

	@Override
	public LiteralContext getCtx() {
		return ctx;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return super.toString() + ", value=[" + value + "]";
	}
}
