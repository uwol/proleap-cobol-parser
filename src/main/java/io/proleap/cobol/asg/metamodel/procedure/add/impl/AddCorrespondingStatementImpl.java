/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.add.impl;

import io.proleap.cobol.CobolParser.AddCorrespondingStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.add.AddCorrespondingStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.To;

public class AddCorrespondingStatementImpl extends CobolDivisionElementImpl implements AddCorrespondingStatement {

	protected final AddCorrespondingStatementContext ctx;

	protected Call fromCall;

	protected To to;

	public AddCorrespondingStatementImpl(final ProgramUnit programUnit, final AddCorrespondingStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getFromCall() {
		return fromCall;
	}

	@Override
	public To getTo() {
		return to;
	}

	@Override
	public void setFromCall(final Call fromCall) {
		this.fromCall = fromCall;
	}

	@Override
	public void setTo(final To to) {
		this.to = to;
	}
}
