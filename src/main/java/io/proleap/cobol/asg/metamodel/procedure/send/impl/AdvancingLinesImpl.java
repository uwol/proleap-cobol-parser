/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.send.impl;

import io.proleap.cobol.Cobol85Parser.SendAdvancingLinesContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.send.AdvancingLines;

public class AdvancingLinesImpl extends CobolDivisionElementImpl implements AdvancingLines {

	protected SendAdvancingLinesContext ctx;

	protected Call linesCall;

	public AdvancingLinesImpl(final ProgramUnit programUnit, final SendAdvancingLinesContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getLinesCall() {
		return linesCall;
	}

	@Override
	public void setLinesCall(final Call linesCall) {
		this.linesCall = linesCall;
	}

}
