/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.valuestmt.impl;

import io.proleap.cobol.Cobol85Parser.BooleanLiteralContext;
import io.proleap.cobol.parser.metamodel.BooleanLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.valuestmt.BooleanLiteralValueStmt;

public class BooleanLiteralValueStmtImpl extends ValueStmtImpl implements BooleanLiteralValueStmt {

	protected BooleanLiteral booleanLiteral;

	public BooleanLiteralValueStmtImpl(final ProgramUnit programUnit, final BooleanLiteralContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public BooleanLiteral getLiteral() {
		return booleanLiteral;
	}

	@Override
	public Boolean getValue() {
		final Boolean result = booleanLiteral != null ? booleanLiteral.getValue() : null;
		return result;
	}

	@Override
	public void setBooleanLiteral(final BooleanLiteral booleanLiteral) {
		this.booleanLiteral = booleanLiteral;
	}

	@Override
	public String toString() {
		return booleanLiteral != null ? booleanLiteral.toString() : null;
	}
}
