/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.CobolParser.RelativeKeyClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.RelativeKeyClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class RelativeKeyClauseImpl extends CobolDivisionElementImpl implements RelativeKeyClause {

	protected final RelativeKeyClauseContext ctx;

	protected Call relativeKeyCall;

	public RelativeKeyClauseImpl(final ProgramUnit programUnit, final RelativeKeyClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getRelativeKeyCall() {
		return relativeKeyCall;
	}

	@Override
	public void setRelativeKeyCall(final Call relativeKeyCall) {
		this.relativeKeyCall = relativeKeyCall;
	}

}
