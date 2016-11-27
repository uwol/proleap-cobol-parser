/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.send.impl;

import io.proleap.cobol.Cobol85Parser.SendStatementAsyncContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.send.Async;

public class AsyncImpl extends CobolDivisionElementImpl implements Async {

	protected SendStatementAsyncContext ctx;

	protected Call dataDescriptionEntryCall;

	protected Type type;

	public AsyncImpl(final ProgramUnit programUnit, final SendStatementAsyncContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDataDescriptionEntryCall() {
		return dataDescriptionEntryCall;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setDataDescriptionEntryCall(final Call dataDescriptionEntryCall) {
		this.dataDescriptionEntryCall = dataDescriptionEntryCall;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
