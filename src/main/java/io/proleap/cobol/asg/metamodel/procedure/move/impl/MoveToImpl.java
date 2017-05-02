/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.move.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.MoveToSendingAreaContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveTo;
import io.proleap.cobol.asg.metamodel.procedure.move.SendingArea;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class MoveToImpl extends CobolDivisionElementImpl implements MoveTo {

	protected final MoveToStatementContext ctx;

	protected List<Call> receivingAreaCalls = new ArrayList<Call>();

	protected SendingArea sendingArea;

	public MoveToImpl(final ProgramUnit programUnit, final MoveToStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addReceivingAreaCall(final Call receivingAreaCall) {
		receivingAreaCalls.add(receivingAreaCall);
	}

	@Override
	public SendingArea addSendingArea(final MoveToSendingAreaContext ctx) {
		SendingArea result = (SendingArea) getASGElement(ctx);

		if (result == null) {
			result = new SendingAreaImpl(programUnit, ctx);

			final ValueStmt sendingAreaValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setSendingAreaValueStmt(sendingAreaValueStmt);

			sendingArea = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Call> getReceivingAreaCalls() {
		return receivingAreaCalls;
	}

	@Override
	public SendingArea getSendingArea() {
		return sendingArea;
	}

}
