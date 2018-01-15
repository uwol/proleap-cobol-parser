/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;

public class UndefinedCallImpl extends CallImpl implements Call {

	protected final CallType callType = CallType.UNDEFINED_CALL;

	public UndefinedCallImpl(final String name, final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(name, programUnit, ctx);
	}

	@Override
	public CallType getCallType() {
		return callType;
	}
}
