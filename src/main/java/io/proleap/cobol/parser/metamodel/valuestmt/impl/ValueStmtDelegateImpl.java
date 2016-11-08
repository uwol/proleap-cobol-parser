/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.valuestmt.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class ValueStmtDelegateImpl extends ValueStmtImpl implements ValueStmt {

	protected final ValueStmt delegate;

	public ValueStmtDelegateImpl(final ValueStmt delegate, final CopyBook copyBook, final CobolScope superScope,
			final ParseTree ctx) {
		super(copyBook, superScope, ctx);

		this.delegate = delegate;
	}

	@Override
	public String toString() {
		return delegate.toString();
	}

}
