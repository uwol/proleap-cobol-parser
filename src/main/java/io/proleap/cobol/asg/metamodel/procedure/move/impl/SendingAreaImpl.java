/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.move.impl;

import io.proleap.cobol.Cobol85Parser.MoveToSendingAreaContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.move.SendingArea;

public class SendingAreaImpl extends CobolDivisionElementImpl implements SendingArea {

	protected final MoveToSendingAreaContext ctx;

	protected Call sendingAreaCall;

	public SendingAreaImpl(final ProgramUnit programUnit, final MoveToSendingAreaContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getSendingAreaCall() {
		return sendingAreaCall;
	}

	@Override
	public void setSendingAreaCall(final Call sendingAreaCall) {
		this.sendingAreaCall = sendingAreaCall;
	}

}
