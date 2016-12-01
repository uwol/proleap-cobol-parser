/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.display.impl;

import io.proleap.cobol.Cobol85Parser.DisplayAtContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.display.At;

public class AtImpl extends CobolDivisionElementImpl implements At {

	protected Call atCall;

	protected DisplayAtContext ctx;

	public AtImpl(final ProgramUnit programUnit, final DisplayAtContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getAtCall() {
		return atCall;
	}

	@Override
	public void setAtCall(final Call atCall) {
		this.atCall = atCall;
	}

}
