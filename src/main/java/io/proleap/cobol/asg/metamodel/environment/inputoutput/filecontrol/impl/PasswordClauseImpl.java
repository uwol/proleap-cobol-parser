/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.CobolParser.PasswordClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.PasswordClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class PasswordClauseImpl extends CobolDivisionElementImpl implements PasswordClause {

	protected final PasswordClauseContext ctx;

	protected Call dataCall;

	public PasswordClauseImpl(final ProgramUnit programUnit, final PasswordClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDataCall() {
		return dataCall;
	}

	@Override
	public void setDataCall(final Call dataCall) {
		this.dataCall = dataCall;
	}

}
