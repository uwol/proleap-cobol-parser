/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.multiply.impl;

import io.proleap.cobol.Cobol85Parser.MultiplyRegularOperandContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.multiply.ByOperand;

public class ByOperandImpl extends CobolDivisionElementImpl implements ByOperand {

	protected final MultiplyRegularOperandContext ctx;

	protected Call operandCall;

	protected boolean rounded;

	public ByOperandImpl(final ProgramUnit programUnit, final MultiplyRegularOperandContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getOperandCall() {
		return operandCall;
	}

	@Override
	public boolean isRounded() {
		return rounded;
	}

	@Override
	public void setOperandCall(final Call operandCall) {
		this.operandCall = operandCall;
	}

	@Override
	public void setRounded(final boolean rounded) {
		this.rounded = rounded;
	}

}
