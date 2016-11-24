/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.divide.impl;

import io.proleap.cobol.Cobol85Parser.DivideGivingContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.divide.Giving;

public class GivingImpl extends CobolDivisionElementImpl implements Giving {

	protected Call call;

	protected DivideGivingContext ctx;

	protected boolean rounded;

	public GivingImpl(final ProgramUnit programUnit, final DivideGivingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getCall() {
		return call;
	}

	@Override
	public boolean isRounded() {
		return rounded;
	}

	@Override
	public void setCall(final Call call) {
		this.call = call;
	}

	@Override
	public void setRounded(final boolean rounded) {
		this.rounded = rounded;
	}

}
