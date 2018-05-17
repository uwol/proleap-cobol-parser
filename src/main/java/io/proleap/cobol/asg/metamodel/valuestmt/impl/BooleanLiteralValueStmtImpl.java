/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.impl;

import io.proleap.cobol.CobolParser.BooleanLiteralContext;
import io.proleap.cobol.asg.metamodel.BooleanLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.BooleanLiteralValueStmt;

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
	public void setBooleanLiteral(final BooleanLiteral booleanLiteral) {
		this.booleanLiteral = booleanLiteral;
	}

	@Override
	public String toString() {
		return booleanLiteral != null ? booleanLiteral.toString() : null;
	}
}
