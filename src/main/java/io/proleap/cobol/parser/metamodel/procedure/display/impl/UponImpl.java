/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.display.impl;

import io.proleap.cobol.Cobol85Parser.DisplayUponContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.display.Upon;

public class UponImpl extends CobolDivisionElementImpl implements Upon {

	protected DisplayUponContext ctx;

	protected Call uponCall;

	public UponImpl(final ProgramUnit programUnit, final DisplayUponContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getUponCall() {
		return uponCall;
	}

	@Override
	public void setUponCall(final Call uponCall) {
		this.uponCall = uponCall;
	}

}
