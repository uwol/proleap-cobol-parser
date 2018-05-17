/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication.impl;

import io.proleap.cobol.CobolParser.EndKeyClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.communication.EndKeyClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class EndKeyClauseImpl extends CobolDivisionElementImpl implements EndKeyClause {

	protected EndKeyClauseContext ctx;

	protected Call dataDescCall;

	public EndKeyClauseImpl(final ProgramUnit programUnit, final EndKeyClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDataDescCall() {
		return dataDescCall;
	}

	@Override
	public void setDataDescCall(final Call dataDescCall) {
		this.dataDescCall = dataDescCall;
	}

}
