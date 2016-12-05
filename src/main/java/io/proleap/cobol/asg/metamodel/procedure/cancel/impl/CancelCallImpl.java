/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.cancel.impl;

import io.proleap.cobol.Cobol85Parser.CancelCallContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.cancel.CancelCall;

public class CancelCallImpl extends CobolDivisionElementImpl implements CancelCall {

	protected Call call;

	protected CancelCallContext ctx;

	protected Type type;

	public CancelCallImpl(final ProgramUnit programUnit, final CancelCallContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getCall() {
		return call;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setCall(final Call call) {
		this.call = call;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
