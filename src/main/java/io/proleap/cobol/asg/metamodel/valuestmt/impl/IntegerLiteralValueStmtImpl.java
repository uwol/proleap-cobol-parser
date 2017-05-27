/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.impl;

import io.proleap.cobol.Cobol85Parser.IntegerLiteralContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.type.Type;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;

public class IntegerLiteralValueStmtImpl extends ValueStmtImpl implements IntegerLiteralValueStmt {

	protected IntegerLiteral integerLiteral;

	public IntegerLiteralValueStmtImpl(final ProgramUnit programUnit, final IntegerLiteralContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public IntegerLiteral getLiteral() {
		return integerLiteral;
	}

	@Override
	public Type getType() {
		return integerLiteral.getType();
	}

	@Override
	public Integer getValue() {
		final Integer result = integerLiteral != null ? integerLiteral.getValue() : null;
		return result;
	}

	@Override
	public void setIntegerLiteral(final IntegerLiteral integerLiteral) {
		this.integerLiteral = integerLiteral;
	}

	@Override
	public String toString() {
		return integerLiteral != null ? integerLiteral.toString() : null;
	}
}
