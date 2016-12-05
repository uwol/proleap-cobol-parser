/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import io.proleap.cobol.Cobol85Parser.IntegerLiteralContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;

public class IntegerLiteralImpl extends CobolDivisionElementImpl implements IntegerLiteral {

	protected final IntegerLiteralContext ctx;

	protected final Integer value;

	public IntegerLiteralImpl(final Integer value, final ProgramUnit programUnit, final IntegerLiteralContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.value = value;
	}

	@Override
	public IntegerLiteralContext getCtx() {
		return ctx;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public String toString() {
		return super.toString() + ", value=[" + value + "]";
	}
}
