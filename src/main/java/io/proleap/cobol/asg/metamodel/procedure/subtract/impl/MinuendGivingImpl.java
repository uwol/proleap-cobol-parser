/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.subtract.impl;

import io.proleap.cobol.Cobol85Parser.SubtractMinuendGivingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.subtract.MinuendGiving;

public class MinuendGivingImpl extends CobolDivisionElementImpl implements MinuendGiving {

	protected final SubtractMinuendGivingContext ctx;

	protected Call minuendCall;

	public MinuendGivingImpl(final ProgramUnit programUnit, final SubtractMinuendGivingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getMinuendCall() {
		return minuendCall;
	}

	@Override
	public void setMinuendCall(final Call minuendCall) {
		this.minuendCall = minuendCall;
	}

}
