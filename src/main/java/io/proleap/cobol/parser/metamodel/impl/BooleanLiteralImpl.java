/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import io.proleap.cobol.Cobol85Parser.BooleanLiteralContext;
import io.proleap.cobol.parser.metamodel.BooleanLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;

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
