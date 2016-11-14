/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.impl;

import io.proleap.cobol.Cobol85Parser.RerunEveryClockContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.RerunEveryClock;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

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
