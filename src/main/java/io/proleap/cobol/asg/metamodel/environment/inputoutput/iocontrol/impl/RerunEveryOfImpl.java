/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.impl;

import io.proleap.cobol.CobolParser.RerunEveryOfContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.RerunEveryOf;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class RerunEveryOfImpl extends CobolDivisionElementImpl implements RerunEveryOf {

	protected final RerunEveryOfContext ctx;

	protected Call fileCall;

	protected RerunEveryOfType rerunEveryOfType;

	public RerunEveryOfImpl(final ProgramUnit programUnit, final RerunEveryOfContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public RerunEveryOfType getRerunEveryOfType() {
		return rerunEveryOfType;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

	@Override
	public void setRerunEveryOfType(final RerunEveryOfType rerunEveryOfType) {
		this.rerunEveryOfType = rerunEveryOfType;
	}
}
