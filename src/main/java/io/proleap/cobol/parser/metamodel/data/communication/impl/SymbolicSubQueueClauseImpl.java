/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication.impl;

import io.proleap.cobol.Cobol85Parser.SymbolicSubQueueClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.data.communication.SymbolicSubQueueClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class SymbolicSubQueueClauseImpl extends CobolDivisionElementImpl implements SymbolicSubQueueClause {

	protected SymbolicSubQueueClauseContext ctx;

	protected Call dataDescCall;

	protected Type type;

	public SymbolicSubQueueClauseImpl(final ProgramUnit programUnit, final SymbolicSubQueueClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDataDescCall() {
		return dataDescCall;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setDataDescCall(final Call dataDescCall) {
		this.dataDescCall = dataDescCall;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
