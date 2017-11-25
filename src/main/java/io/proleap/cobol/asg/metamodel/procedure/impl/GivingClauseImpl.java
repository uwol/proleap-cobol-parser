/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import io.proleap.cobol.Cobol85Parser.ProcedureDivisionGivingClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.GivingClause;

public class GivingClauseImpl extends CobolDivisionElementImpl implements GivingClause {

	protected final ProcedureDivisionGivingClauseContext ctx;

	protected Call givingCall;

	public GivingClauseImpl(final ProgramUnit programUnit, final ProcedureDivisionGivingClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getGivingCall() {
		return givingCall;
	}

	@Override
	public void setGivingCall(final Call givingCall) {
		this.givingCall = givingCall;
	}
}
