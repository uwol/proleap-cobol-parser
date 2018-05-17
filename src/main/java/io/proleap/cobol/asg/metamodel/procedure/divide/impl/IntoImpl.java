/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide.impl;

import io.proleap.cobol.CobolParser.DivideIntoContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.divide.Into;

public class IntoImpl extends CobolDivisionElementImpl implements Into {

	protected DivideIntoContext ctx;

	protected Call givingCall;

	protected boolean rounded;

	public IntoImpl(final ProgramUnit programUnit, final DivideIntoContext ctx) {
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
