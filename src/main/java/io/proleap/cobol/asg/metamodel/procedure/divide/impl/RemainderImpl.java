/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide.impl;

import io.proleap.cobol.CobolParser.DivideRemainderContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.divide.Remainder;

public class RemainderImpl extends CobolDivisionElementImpl implements Remainder {

	protected DivideRemainderContext ctx;

	protected Call remainderCall;

	public RemainderImpl(final ProgramUnit programUnit, final DivideRemainderContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getRemainderCall() {
		return remainderCall;
	}

	@Override
	public void setRemainderCall(final Call remainderCall) {
		this.remainderCall = remainderCall;
	}
}
