/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.stop.impl;

import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.stop.StopStatement;

public class StopStatementImpl extends StatementImpl implements StopStatement {

	protected final StopStatementContext ctx;

	protected Call displayCall;

	protected Type type;

	public StopStatementImpl(final ProgramUnit programUnit, final StopStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDisplayCall() {
		return displayCall;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setDisplayCall(final Call displayCall) {
		this.displayCall = displayCall;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
