/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.use.impl;

import io.proleap.cobol.CobolParser.UseDebugOnContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.use.DebugOn;

public class DebugOnImpl extends CobolDivisionElementImpl implements DebugOn {

	protected UseDebugOnContext ctx;

	protected DebugOnType debugOnType;

	protected Call onCall;

	public DebugOnImpl(final ProgramUnit programUnit, final UseDebugOnContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DebugOnType getDebugOnType() {
		return debugOnType;
	}

	@Override
	public Call getOnCall() {
		return onCall;
	}

	@Override
	public void setOnCall(final Call onCall) {
		this.onCall = onCall;
	}

	@Override
	public void setType(final DebugOnType debugOnType) {
		this.debugOnType = debugOnType;
	}

}
