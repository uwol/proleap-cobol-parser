/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.add.impl;

import io.proleap.cobol.CobolParser.AddToContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.add.To;

public class ToImpl extends CobolDivisionElementImpl implements To {

	protected final AddToContext ctx;

	protected boolean rounded;

	protected Call toCall;

	public ToImpl(final ProgramUnit programUnit, final AddToContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getToCall() {
		return toCall;
	}

	@Override
	public boolean isRounded() {
		return rounded;
	}

	@Override
	public void setRounded(final boolean rounded) {
		this.rounded = rounded;
	}

	@Override
	public void setToCall(final Call toCall) {
		this.toCall = toCall;
	}
}
