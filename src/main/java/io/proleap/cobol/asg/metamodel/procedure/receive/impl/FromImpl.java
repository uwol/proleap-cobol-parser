/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive.impl;

import io.proleap.cobol.Cobol85Parser.ReceiveFromContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.receive.From;

public class FromImpl extends CobolDivisionElementImpl implements From {

	protected final ReceiveFromContext ctx;

	protected FromType fromType;

	protected Call threadCall;

	public FromImpl(final ProgramUnit programUnit, final ReceiveFromContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public FromType getFromType() {
		return fromType;
	}

	@Override
	public Call getThreadCall() {
		return threadCall;
	}

	@Override
	public void setFromType(final FromType fromType) {
		this.fromType = fromType;
	}

	@Override
	public void setThreadCall(final Call threadCall) {
		this.threadCall = threadCall;
	}

}
