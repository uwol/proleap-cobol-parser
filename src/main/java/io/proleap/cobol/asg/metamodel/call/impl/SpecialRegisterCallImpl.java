/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call.impl;

import io.proleap.cobol.Cobol85Parser.SpecialRegisterContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.SpecialRegisterCall;
import io.proleap.cobol.asg.metamodel.type.CobolBaseType;
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
		final Type result;

		switch (type) {
		case ADDRESS_OF:
			result = CobolBaseType.INTEGER;
			break;
		case LENGTH_OF:
			result = CobolBaseType.INTEGER;
			break;
		case LINAGE_COUNTER:
			result = CobolBaseType.INTEGER;
			break;
		case LINE_COUNTER:
			result = CobolBaseType.INTEGER;
			break;
		case PAGE_COUNTER:
			result = CobolBaseType.INTEGER;
			break;
		case SORT_FILE_SIZE:
			result = CobolBaseType.INTEGER;
			break;
		default:
			result = null;
			break;
		}

		return result;
	}

	@Override
	public void setIdentifierCall(final Call identifierCall) {
		this.identifierCall = identifierCall;
	}
}
