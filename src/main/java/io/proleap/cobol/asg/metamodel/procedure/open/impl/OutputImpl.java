/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.open.impl;

import io.proleap.cobol.CobolParser.OpenOutputContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.open.Output;

public class OutputImpl extends CobolDivisionElementImpl implements Output {

	protected final OpenOutputContext ctx;

	protected Call fileCall;

	protected boolean noRewind;

	public OutputImpl(final ProgramUnit programUnit, final OpenOutputContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public boolean isNoRewind() {
		return noRewind;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

	@Override
	public void setNoRewind(final boolean noRewind) {
		this.noRewind = noRewind;
	}

}
