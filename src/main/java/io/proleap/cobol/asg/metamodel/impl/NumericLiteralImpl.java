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

	protected final Double value;

	public NumericLiteralImpl(final Double value, final ProgramUnit programUnit, final NumericLiteralContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.value = value;
	}

	@Override
	public NumericLiteralContext getCtx() {
		return ctx;
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return super.toString() + ", value=[" + value + "]";
	}
}
