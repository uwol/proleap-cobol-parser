/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.receive.impl;

import io.proleap.cobol.Cobol85Parser.ReceiveThreadContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.receive.Thread;

public class ThreadImpl extends CobolDivisionElementImpl implements Thread {

	protected final ReceiveThreadContext ctx;

	protected Call threadInCall;

	public ThreadImpl(final ProgramUnit programUnit, final ReceiveThreadContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getThreadInCall() {
		return threadInCall;
	}

	@Override
	public void setThreadInCall(final Call threadInCall) {
		this.threadInCall = threadInCall;
	}

}
