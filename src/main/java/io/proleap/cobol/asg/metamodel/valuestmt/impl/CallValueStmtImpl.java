/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;

public class CallValueStmtImpl extends ValueStmtImpl implements CallValueStmt {

	protected Call call;

	public CallValueStmtImpl(final Call call, final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(programUnit, ctx);

		this.call = call;
	}

	@Override
	public Call getCall() {
		return call;
	}

	@Override
	public Object getValue() {
		return null;
	}

}
