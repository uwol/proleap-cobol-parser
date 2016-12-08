/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.exhibit.impl;

import io.proleap.cobol.Cobol85Parser.ExhibitOperandContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.exhibit.Operand;

public class OperandImpl extends CobolDivisionElementImpl implements Operand {

	protected ExhibitOperandContext ctx;

	protected Call operandCall;

	public OperandImpl(final ProgramUnit programUnit, final ExhibitOperandContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getOperandCall() {
		return operandCall;
	}

	@Override
	public void setOperandCall(final Call operandCall) {
		this.operandCall = operandCall;
	}

}
