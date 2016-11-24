/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.divide.impl;

import io.proleap.cobol.Cobol85Parser.DivideRemainderContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.divide.Remainder;

public class RemainderImpl extends CobolDivisionElementImpl implements Remainder {

	protected DivideRemainderContext ctx;

	protected Call remainderCall;

	public RemainderImpl(final ProgramUnit programUnit, final DivideRemainderContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getRemainderCall() {
		return remainderCall;
	}

	@Override
	public void setRemainderCall(final Call remainderCall) {
		this.remainderCall = remainderCall;
	}

}
