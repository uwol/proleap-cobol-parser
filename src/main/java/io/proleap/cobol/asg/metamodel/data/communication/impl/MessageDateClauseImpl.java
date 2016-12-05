/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication.impl;

import io.proleap.cobol.Cobol85Parser.MessageDateClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.communication.MessageDateClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class MessageDateClauseImpl extends CobolDivisionElementImpl implements MessageDateClause {

	protected MessageDateClauseContext ctx;

	protected Call dataDescCall;

	public MessageDateClauseImpl(final ProgramUnit programUnit, final MessageDateClauseContext ctx) {
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
