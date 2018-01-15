/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import io.proleap.cobol.Cobol85Parser.NumericLiteralContext;
import io.proleap.cobol.asg.metamodel.NumericLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.type.CobolBaseType;
import io.proleap.cobol.asg.metamodel.type.Type;

public class NumericLiteralImpl extends CobolDivisionElementImpl implements NumericLiteral {

	protected final NumericLiteralContext ctx;

	protected Double floatValue;

	protected Long integerValue;

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
	public Double getFloatValue() {
		return floatValue;
	}

	@Override
	public Long getIntegerValue() {
		return integerValue;
	}

	@Override
	public NumericLiteralType getNumericLiteralType() {
		return numericLiteralType;
	}

	@Override
	public Type getType() {
		final Type result;

		switch (numericLiteralType) {
		case INTEGER:
			result = CobolBaseType.INTEGER;
			break;
		case FLOAT:
		default:
			result = CobolBaseType.FLOAT;
			break;
		}

		return result;
	}

	@Override
	public Object getValue() {
		final Object result;

		switch (numericLiteralType) {
		case INTEGER:
			result = integerValue;
			break;
		case FLOAT:
		default:
			result = floatValue;
			break;
		}

		return result;
	}

	@Override
	public void setFloatValue(final Double doubleValue) {
		floatValue = doubleValue;
	}

	@Override
	public void setIntegerValue(final Long integerValue) {
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
