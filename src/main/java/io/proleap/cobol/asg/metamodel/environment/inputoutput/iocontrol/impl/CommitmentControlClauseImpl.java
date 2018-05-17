/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.impl;

import io.proleap.cobol.CobolParser.CommitmentControlClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.CommitmentControlClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class CommitmentControlClauseImpl extends CobolDivisionElementImpl implements CommitmentControlClause {

	protected final CommitmentControlClauseContext ctx;

	protected Call fileCall;

	public CommitmentControlClauseImpl(final ProgramUnit programUnit, final CommitmentControlClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

}
