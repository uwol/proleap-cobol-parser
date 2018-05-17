/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.impl;

import io.proleap.cobol.CobolParser.RerunEveryClockContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.RerunEveryClock;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class RerunEveryClockImpl extends CobolDivisionElementImpl implements RerunEveryClock {

	protected IntegerLiteral clockUnits;

	protected final RerunEveryClockContext ctx;

	public RerunEveryClockImpl(final ProgramUnit programUnit, final RerunEveryClockContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getClockUnits() {
		return clockUnits;
	}

	@Override
	public void setClockUnits(final IntegerLiteral clockUnits) {
		this.clockUnits = clockUnits;
	}

}
