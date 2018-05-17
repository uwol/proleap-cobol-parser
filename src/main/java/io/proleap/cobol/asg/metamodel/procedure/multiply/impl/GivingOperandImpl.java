/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.multiply.impl;

import io.proleap.cobol.CobolParser.MultiplyGivingOperandContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.multiply.GivingOperand;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class GivingOperandImpl extends CobolDivisionElementImpl implements GivingOperand {

	protected final MultiplyGivingOperandContext ctx;

	protected ValueStmt operandValueStmt;

	public GivingOperandImpl(final ProgramUnit programUnit, final MultiplyGivingOperandContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getOperandValueStmt() {
		return operandValueStmt;
	}

	@Override
	public void setOperandValueStmt(final ValueStmt operandValueStmt) {
		this.operandValueStmt = operandValueStmt;
	}
}
