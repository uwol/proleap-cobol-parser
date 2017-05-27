/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call.impl;

import io.proleap.cobol.Cobol85Parser.SpecialRegisterContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.SpecialRegisterCall;
import io.proleap.cobol.asg.metamodel.type.Type;

public class SpecialRegisterCallImpl extends CallImpl implements SpecialRegisterCall {

	protected final CallType callType = CallType.SPECIAL_REGISTER_CALL;

	protected final SpecialRegisterContext ctx;

	protected Call identifierCall;

	protected final SpecialRegisterType type;

	public SpecialRegisterCallImpl(final SpecialRegisterType type, final ProgramUnit programUnit,
			final SpecialRegisterContext ctx) {
		super(null, programUnit, ctx);

		this.ctx = ctx;
		this.type = type;
	}

	@Override
	public CallType getCallType() {
		return callType;
	}

	@Override
	public SpecialRegisterContext getCtx() {
		return ctx;
	}

	@Override
	public Call getIdentifierCall() {
		return identifierCall;
	}

	@Override
	public SpecialRegisterType getSpecialRegisterType() {
		return type;
	}

	@Override
	public Type getType() {
		return null;
	}

	@Override
	public void setIdentifierCall(final Call identifierCall) {
		this.identifierCall = identifierCall;
	}

}
