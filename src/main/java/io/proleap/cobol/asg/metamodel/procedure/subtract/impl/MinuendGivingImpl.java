/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.subtract.impl;

import io.proleap.cobol.CobolParser.SubtractMinuendGivingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.subtract.MinuendGiving;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class MinuendGivingImpl extends CobolDivisionElementImpl implements MinuendGiving {

	protected final SubtractMinuendGivingContext ctx;

	protected ValueStmt minuendValueStmt;

	public MinuendGivingImpl(final ProgramUnit programUnit, final SubtractMinuendGivingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getMinuendValueStmt() {
		return minuendValueStmt;
	}

	@Override
	public void setMinuendValueStmt(final ValueStmt minuendValueStmt) {
		this.minuendValueStmt = minuendValueStmt;
	}
}
