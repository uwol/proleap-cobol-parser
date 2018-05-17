/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring.impl;

import io.proleap.cobol.CobolParser.UnstringCountInContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.unstring.CountIn;

public class CountInImpl extends CobolDivisionElementImpl implements CountIn {

	protected Call countInCall;

	protected final UnstringCountInContext ctx;

	public CountInImpl(final ProgramUnit programUnit, final UnstringCountInContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getCountInCall() {
		return countInCall;
	}

	@Override
	public void setCountInCall(final Call countInCall) {
		this.countInCall = countInCall;
	}

}
