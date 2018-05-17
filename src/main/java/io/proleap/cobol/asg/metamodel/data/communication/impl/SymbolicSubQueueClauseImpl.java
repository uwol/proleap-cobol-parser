/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication.impl;

import io.proleap.cobol.CobolParser.SymbolicSubQueueClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.communication.SymbolicSubQueueClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class SymbolicSubQueueClauseImpl extends CobolDivisionElementImpl implements SymbolicSubQueueClause {

	protected SymbolicSubQueueClauseContext ctx;

	protected Call dataDescCall;

	protected SymbolicSubQueueClauseType symbolicSubQueueClauseType;

	public SymbolicSubQueueClauseImpl(final ProgramUnit programUnit, final SymbolicSubQueueClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDataDescCall() {
		return dataDescCall;
	}

	@Override
	public SymbolicSubQueueClauseType getSymbolicSubQueueClauseType() {
		return symbolicSubQueueClauseType;
	}

	@Override
	public void setDataDescCall(final Call dataDescCall) {
		this.dataDescCall = dataDescCall;
	}

	@Override
	public void setSymbolicSubQueueClauseType(final SymbolicSubQueueClauseType symbolicSubQueueClauseType) {
		this.symbolicSubQueueClauseType = symbolicSubQueueClauseType;
	}

}
