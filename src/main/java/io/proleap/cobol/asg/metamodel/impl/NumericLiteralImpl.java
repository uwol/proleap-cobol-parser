/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import io.proleap.cobol.Cobol85Parser.NumericLiteralContext;
import io.proleap.cobol.asg.metamodel.NumericLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;

public class NumericLiteralImpl extends CobolDivisionElementImpl implements NumericLiteral {

	protected final NumericLiteralContext ctx;

	protected Double doubleValue;

	protected Integer integerValue;

	protected NumericLiteralType numericLiteralType;

	public NumericLiteralImpl(final ProgramUnit programUnit, final NumericLiteralContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public NumericLiteralContext getCtx() {
		return ctx;
	}

	@Override
	public Double getDoubleValue() {
		return doubleValue;
	}

	@Override
	public Integer getIntegerValue() {
		return integerValue;
	}

	@Override
	public NumericLiteralType getNumericLiteralType() {
		return numericLiteralType;
	}

	@Override
	public Object getValue() {
		final Object result;

		switch (numericLiteralType) {
		case INTEGER:
			result = integerValue;
			break;
		case DOUBLE:
		default:
			result = doubleValue;
			break;
		}

		return result;
	}

	@Override
	public void setDoubleValue(final Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	@Override
	public void setIntegerValue(final Integer integerValue) {
		this.integerValue = integerValue;
	}

	@Override
	public void setNumericLiteralType(final NumericLiteralType numericLiteralType) {
		this.numericLiteralType = numericLiteralType;
	}

	@Override
	public String toString() {
		return super.toString() + ", value=[" + getValue() + "]";
	}
}
