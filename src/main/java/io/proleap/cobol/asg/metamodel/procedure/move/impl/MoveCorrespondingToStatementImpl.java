/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.move.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.MoveCorrespondingToSendingAreaContext;
import io.proleap.cobol.CobolParser.MoveCorrespondingToStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveCorrespondingToStatetement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveCorrespondingToSendingArea;

public class MoveCorrespondingToStatementImpl extends CobolDivisionElementImpl implements MoveCorrespondingToStatetement {

	protected final MoveCorrespondingToStatementContext ctx;

	protected MoveCorrespondingToSendingArea moveToCorrespondingSendingArea;

	protected List<Call> receivingAreaCalls = new ArrayList<Call>();

	public MoveCorrespondingToStatementImpl(final ProgramUnit programUnit, final MoveCorrespondingToStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public MoveCorrespondingToSendingArea addMoveCorrespondingToSendingArea(
			final MoveCorrespondingToSendingAreaContext ctx) {
		MoveCorrespondingToSendingArea result = (MoveCorrespondingToSendingArea) getASGElement(ctx);

		if (result == null) {
			result = new MoveCorrespondingToSendingAreaImpl(programUnit, ctx);

			result.setSendingAreaCall(createCall(ctx.identifier()));

			moveToCorrespondingSendingArea = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public void addReceivingAreaCall(final Call receivingAreaCall) {
		receivingAreaCalls.add(receivingAreaCall);
	}

	@Override
	public MoveCorrespondingToSendingArea getMoveToCorrespondingSendingArea() {
		return moveToCorrespondingSendingArea;
	}

	@Override
	public List<Call> getReceivingAreaCalls() {
		return receivingAreaCalls;
	}
}
