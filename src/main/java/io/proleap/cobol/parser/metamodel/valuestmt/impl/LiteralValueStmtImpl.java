/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.valuestmt.impl;

import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Literal;
import io.proleap.cobol.parser.metamodel.valuestmt.LiteralValueStmt;

public class LiteralValueStmtImpl extends ValueStmtImpl implements LiteralValueStmt {

	protected Literal literal;

	public LiteralValueStmtImpl(final CopyBook copyBook, final CobolScope superScope, final LiteralContext ctx) {
		super(copyBook, superScope, ctx);
	}

	@Override
	public Literal getLiteral() {
		return literal;
	}

	@Override
	public Literal getValue() {
		return literal;
	}

	@Override
	public void setLiteral(final Literal literal) {
		this.literal = literal;
	}

	@Override
	public String toString() {
		return literal.toString();
	}
}
