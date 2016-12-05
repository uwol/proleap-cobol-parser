/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.Cobol85Parser.RelativeKeyClauseContext;
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
