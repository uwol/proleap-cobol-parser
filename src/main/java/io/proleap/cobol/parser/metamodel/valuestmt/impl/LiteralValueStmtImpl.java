/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.valuestmt.impl;

import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.Literal;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.valuestmt.LiteralValueStmt;

public class LiteralValueStmtImpl extends ValueStmtImpl implements LiteralValueStmt {

	protected Literal literal;

	public LiteralValueStmtImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final LiteralContext ctx) {
		super(programUnit, scope, ctx);
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
