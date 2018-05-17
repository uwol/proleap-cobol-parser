/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionControlClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.screen.ControlClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ControlClauseImpl extends CobolDivisionElementImpl implements ControlClause {

	protected Call controlCall;

	protected ScreenDescriptionControlClauseContext ctx;

	public ControlClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionControlClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getControlCall() {
		return controlCall;
	}

	@Override
	public void setControlCall(final Call controlCall) {
		this.controlCall = controlCall;
	}

}
