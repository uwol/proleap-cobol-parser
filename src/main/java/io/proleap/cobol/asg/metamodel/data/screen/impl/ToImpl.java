/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionToClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.screen.To;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ToImpl extends CobolDivisionElementImpl implements To {

	protected ScreenDescriptionToClauseContext ctx;

	protected Call toCall;

	public ToImpl(final ProgramUnit programUnit, final ScreenDescriptionToClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getToCall() {
		return toCall;
	}

	@Override
	public void setToCall(final Call toCall) {
		this.toCall = toCall;
	}

}
