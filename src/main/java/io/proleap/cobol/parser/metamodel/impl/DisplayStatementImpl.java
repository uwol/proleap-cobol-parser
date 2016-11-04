/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import io.proleap.cobol.Cobol85Parser.DisplayStatementContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.DisplayStatement;

public class DisplayStatementImpl extends CobolScopedElementImpl implements DisplayStatement {

	protected final DisplayStatementContext ctx;

	public DisplayStatementImpl(final CopyBook copyBook, final CobolScope superScope,
			final DisplayStatementContext ctx) {
		super(copyBook, superScope, ctx);

		this.ctx = ctx;
	}

}
