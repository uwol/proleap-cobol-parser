/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.multiply.impl;

import io.proleap.cobol.CobolParser.MultiplyGivingResultContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.multiply.GivingResult;

public class GivingResultImpl extends CobolDivisionElementImpl implements GivingResult {

	protected final MultiplyGivingResultContext ctx;

	protected Call resultCall;

	protected boolean rounded;

	public GivingResultImpl(final ProgramUnit programUnit, final MultiplyGivingResultContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getResultCall() {
		return resultCall;
	}

	@Override
	public boolean isRounded() {
		return rounded;
	}

	@Override
	public void setResultCall(final Call resultCall) {
		this.resultCall = resultCall;
	}

	@Override
	public void setRounded(final boolean rounded) {
		this.rounded = rounded;
	}
}
