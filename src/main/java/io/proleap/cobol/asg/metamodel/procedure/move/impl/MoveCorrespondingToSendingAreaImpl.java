/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.move.impl;

import io.proleap.cobol.CobolParser.MoveCorrespondingToSendingAreaContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveCorrespondingToSendingArea;

public class MoveCorrespondingToSendingAreaImpl extends CobolDivisionElementImpl
		implements MoveCorrespondingToSendingArea {

	protected final MoveCorrespondingToSendingAreaContext ctx;

	protected Call sendingAreaCall;

	public MoveCorrespondingToSendingAreaImpl(final ProgramUnit programUnit,
			final MoveCorrespondingToSendingAreaContext ctx) {
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
