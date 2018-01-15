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
import io.proleap.cobol.asg.metamodel.call.IndexCall;
import io.proleap.cobol.asg.metamodel.data.datadescription.Index;

public class IndexCallImpl extends CallImpl implements IndexCall {

	protected final CallType callType = CallType.INDEX_CALL;

	protected final Index index;

	public IndexCallImpl(final String name, final Index index, final ProgramUnit programUnit,
			final ParserRuleContext ctx) {
		super(name, programUnit, ctx);

		this.index = index;
	}

	@Override
	public CallType getCallType() {
		return callType;
	}

	@Override
	public Index getIndex() {
		return index;
	}
}
