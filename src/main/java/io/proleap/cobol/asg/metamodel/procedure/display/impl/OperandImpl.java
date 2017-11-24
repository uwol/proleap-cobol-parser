/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.display.impl;

import io.proleap.cobol.Cobol85Parser.DisplayOperandContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.display.Operand;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class OperandImpl extends CobolDivisionElementImpl implements Operand {

	protected DisplayOperandContext ctx;

	protected ValueStmt operandValueStmt;

	public OperandImpl(final ProgramUnit programUnit, final DisplayOperandContext ctx) {
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
