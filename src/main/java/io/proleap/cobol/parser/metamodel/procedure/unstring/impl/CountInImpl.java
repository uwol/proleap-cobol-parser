/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.unstring.impl;

import io.proleap.cobol.Cobol85Parser.UnstringCountInContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.unstring.CountIn;

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
