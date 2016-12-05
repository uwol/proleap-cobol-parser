/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication.impl;

import io.proleap.cobol.Cobol85Parser.MessageCountClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.communication.MessageCountClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class MessageCountClauseImpl extends CobolDivisionElementImpl implements MessageCountClause {

	protected MessageCountClauseContext ctx;

	protected Call dataDescCall;

	public MessageCountClauseImpl(final ProgramUnit programUnit, final MessageCountClauseContext ctx) {
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
