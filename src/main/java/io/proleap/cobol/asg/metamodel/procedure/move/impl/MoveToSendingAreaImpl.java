/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.move.impl;

import io.proleap.cobol.CobolParser.MoveToSendingAreaContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveToSendingArea;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class MoveToSendingAreaImpl extends CobolDivisionElementImpl implements MoveToSendingArea {

	protected final MoveToSendingAreaContext ctx;

	protected ValueStmt sendingAreaValueStmt;

	public MoveToSendingAreaImpl(final ProgramUnit programUnit, final MoveToSendingAreaContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getSendingAreaValueStmt() {
		return sendingAreaValueStmt;
	}

	@Override
	public void setSendingAreaValueStmt(final ValueStmt sendingAreaValueStmt) {
		this.sendingAreaValueStmt = sendingAreaValueStmt;
	}
}
