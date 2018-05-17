/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import java.math.BigDecimal;

import io.proleap.cobol.CobolParser.IntegerLiteralContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;

public class IntegerLiteralImpl extends CobolDivisionElementImpl implements IntegerLiteral {

	protected final IntegerLiteralContext ctx;

	protected final BigDecimal value;

	public IntegerLiteralImpl(final BigDecimal value, final ProgramUnit programUnit, final IntegerLiteralContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.value = value;
	}

	@Override
	public IntegerLiteralContext getCtx() {
		return ctx;
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	@Override
	public String toString() {
		return super.toString() + ", value=[" + value + "]";
	}
}
