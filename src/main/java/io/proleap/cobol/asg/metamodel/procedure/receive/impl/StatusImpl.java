/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive.impl;

import io.proleap.cobol.CobolParser.ReceiveStatusContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.receive.Status;

public class StatusImpl extends CobolDivisionElementImpl implements Status {

	protected final ReceiveStatusContext ctx;

	protected Call statusCall;

	public StatusImpl(final ProgramUnit programUnit, final ReceiveStatusContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getStatusCall() {
		return statusCall;
	}

	@Override
	public void setStatusCall(final Call statusCall) {
		this.statusCall = statusCall;
	}

}
