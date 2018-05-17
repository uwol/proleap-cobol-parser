/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.open.impl;

import io.proleap.cobol.CobolParser.OpenInputContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.open.Input;

public class InputImpl extends CobolDivisionElementImpl implements Input {

	protected final OpenInputContext ctx;

	protected Call fileCall;

	protected InputType inputType;

	public InputImpl(final ProgramUnit programUnit, final OpenInputContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public InputType getInputType() {
		return inputType;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

	@Override
	public void setInputType(final InputType inputType) {
		this.inputType = inputType;
	}

}
