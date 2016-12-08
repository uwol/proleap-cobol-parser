/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionControlClauseContext;
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
