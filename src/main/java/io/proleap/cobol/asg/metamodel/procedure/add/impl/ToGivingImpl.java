/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.add.impl;

import io.proleap.cobol.CobolParser.AddToGivingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.add.ToGiving;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ToGivingImpl extends CobolDivisionElementImpl implements ToGiving {

	protected final AddToGivingContext ctx;

	protected ValueStmt toValueStmt;

	public ToGivingImpl(final ProgramUnit programUnit, final AddToGivingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getToValueStmt() {
		return toValueStmt;
	}

	@Override
	public void setToValueStmt(final ValueStmt toValueStmt) {
		this.toValueStmt = toValueStmt;
	}
}
