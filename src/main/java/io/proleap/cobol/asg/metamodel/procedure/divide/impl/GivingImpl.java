/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide.impl;

import io.proleap.cobol.CobolParser.DivideGivingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.divide.Giving;

public class GivingImpl extends CobolDivisionElementImpl implements Giving {

	protected DivideGivingContext ctx;

	protected Call givingCall;

	protected boolean rounded;

	public GivingImpl(final ProgramUnit programUnit, final DivideGivingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getGivingCall() {
		return givingCall;
	}

	@Override
	public boolean isRounded() {
		return rounded;
	}

	@Override
	public void setGivingCall(final Call givingCall) {
		this.givingCall = givingCall;
	}

	@Override
	public void setRounded(final boolean rounded) {
		this.rounded = rounded;
	}
}
