/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.CobolParser.FileStatusClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileStatusClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class FileStatusClauseImpl extends CobolDivisionElementImpl implements FileStatusClause {

	protected final FileStatusClauseContext ctx;

	protected Call dataCall;

	protected Call dataCall2;

	public FileStatusClauseImpl(final ProgramUnit programUnit, final FileStatusClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDataCall() {
		return dataCall;
	}

	@Override
	public Call getDataCall2() {
		return dataCall2;
	}

	@Override
	public void setDataCall(final Call dataCall) {
		this.dataCall = dataCall;
	}

	@Override
	public void setDataCall2(final Call dataCall2) {
		this.dataCall2 = dataCall2;
	}

}
