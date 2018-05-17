/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.send.impl;

import io.proleap.cobol.CobolParser.SendStatementAsyncContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.send.Async;

public class AsyncImpl extends CobolDivisionElementImpl implements Async {

	protected AsyncType asyncType;

	protected SendStatementAsyncContext ctx;

	protected Call dataDescriptionEntryCall;

	public AsyncImpl(final ProgramUnit programUnit, final SendStatementAsyncContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AsyncType getAsyncType() {
		return asyncType;
	}

	@Override
	public Call getDataDescriptionEntryCall() {
		return dataDescriptionEntryCall;
	}

	@Override
	public void setAsyncType(final AsyncType asyncType) {
		this.asyncType = asyncType;
	}

	@Override
	public void setDataDescriptionEntryCall(final Call dataDescriptionEntryCall) {
		this.dataDescriptionEntryCall = dataDescriptionEntryCall;
	}

}
