/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionUsingClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.screen.UsingClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class UsingClauseImpl extends CobolDivisionElementImpl implements UsingClause {

	protected ScreenDescriptionUsingClauseContext ctx;

	protected Call usingCall;

	public UsingClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionUsingClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getUsingCall() {
		return usingCall;
	}

	@Override
	public void setUsingCall(final Call usingCall) {
		this.usingCall = usingCall;
	}
}
