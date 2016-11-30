/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.impl;

import io.proleap.cobol.Cobol85Parser.CommitmentControlClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.CommitmentControlClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

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
