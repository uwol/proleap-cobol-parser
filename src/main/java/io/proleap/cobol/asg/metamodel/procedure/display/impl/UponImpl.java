/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.display.impl;

import io.proleap.cobol.CobolParser.DisplayUponContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.display.Upon;

public class UponImpl extends CobolDivisionElementImpl implements Upon {

	protected DisplayUponContext ctx;

	protected Call uponCall;

	public UponImpl(final ProgramUnit programUnit, final DisplayUponContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getUponCall() {
		return uponCall;
	}

	@Override
	public void setUponCall(final Call uponCall) {
		this.uponCall = uponCall;
	}
}
