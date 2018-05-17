/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import java.math.BigDecimal;

import io.proleap.cobol.CobolParser.NumericLiteralContext;
import io.proleap.cobol.asg.metamodel.NumericLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;

public class NumericLiteralImpl extends CobolDivisionElementImpl implements NumericLiteral {

	protected final NumericLiteralContext ctx;

	protected NumericLiteralType numericLiteralType;

	protected BigDecimal value;

	public NumericLiteralImpl(final ProgramUnit programUnit, final NumericLiteralContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public NumericLiteralContext getCtx() {
		return ctx;
	}

	@Override
	public NumericLiteralType getNumericLiteralType() {
		return numericLiteralType;
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	@Override
	public void setNumericLiteralType(final NumericLiteralType numericLiteralType) {
		this.numericLiteralType = numericLiteralType;
	}

	@Override
	public void setValue(final BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return super.toString() + ", value=[" + getValue() + "]";
	}
}
