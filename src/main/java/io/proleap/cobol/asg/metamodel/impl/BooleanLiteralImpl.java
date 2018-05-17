/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import io.proleap.cobol.CobolParser.BooleanLiteralContext;
import io.proleap.cobol.asg.metamodel.BooleanLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;

public class BooleanLiteralImpl extends CobolDivisionElementImpl implements BooleanLiteral {

	protected final BooleanLiteralContext ctx;

	protected final Boolean value;

	public BooleanLiteralImpl(final Boolean value, final ProgramUnit programUnit, final BooleanLiteralContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.value = value;
	}

	@Override
	public BooleanLiteralContext getCtx() {
		return ctx;
	}

	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public String toString() {
		return super.toString() + ", value=[" + value + "]";
	}
}
