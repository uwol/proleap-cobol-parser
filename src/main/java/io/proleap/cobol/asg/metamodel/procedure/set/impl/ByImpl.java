/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.set.impl;

import io.proleap.cobol.CobolParser.SetByValueContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.set.By;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ByImpl extends CobolDivisionElementImpl implements By {

	protected ValueStmt byValueStmt;

	protected SetByValueContext ctx;

	public ByImpl(final ProgramUnit programUnit, final SetByValueContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getByValueStmt() {
		return byValueStmt;
	}

	@Override
	public void setByValueStmt(final ValueStmt byValueStmt) {
		this.byValueStmt = byValueStmt;
	}

}
