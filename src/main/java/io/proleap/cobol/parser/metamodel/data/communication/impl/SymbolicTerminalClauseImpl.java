/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication.impl;

import io.proleap.cobol.Cobol85Parser.SymbolicTerminalClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.data.communication.SymbolicTerminalClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class SymbolicTerminalClauseImpl extends CobolDivisionElementImpl implements SymbolicTerminalClause {

	protected SymbolicTerminalClauseContext ctx;

	protected Call dataDescCall;

	public SymbolicTerminalClauseImpl(final ProgramUnit programUnit, final SymbolicTerminalClauseContext ctx) {
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
