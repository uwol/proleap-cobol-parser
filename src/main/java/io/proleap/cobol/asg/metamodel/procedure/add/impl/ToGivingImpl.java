/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.add.impl;

import io.proleap.cobol.Cobol85Parser.AddToGivingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.add.ToGiving;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ToGivingImpl extends CobolDivisionElementImpl implements ToGiving {

	protected final AddToGivingContext ctx;

	protected ValueStmt to;

	public ToGivingImpl(final ProgramUnit programUnit, final AddToGivingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getTo() {
		return to;
	}

	@Override
	public void setTo(final ValueStmt to) {
		this.to = to;
	}
}
