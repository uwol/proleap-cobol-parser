/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.receive.impl;

import io.proleap.cobol.Cobol85Parser.ReceiveStatusContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.receive.Status;

public class StatusImpl extends CobolDivisionElementImpl implements Status {

	protected final ReceiveStatusContext ctx;

	protected Call statusCall;

	public StatusImpl(final ProgramUnit programUnit, final ReceiveStatusContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getStatusCall() {
		return statusCall;
	}

	@Override
	public void setStatusCall(final Call statusCall) {
		this.statusCall = statusCall;
	}

}
