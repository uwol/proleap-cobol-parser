/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import io.proleap.cobol.Cobol85Parser.PerformByContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.By;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ByImpl extends CobolDivisionElementImpl implements By {

	protected ValueStmt byValueStmt;

	protected final PerformByContext ctx;

	public ByImpl(final ProgramUnit programUnit, final PerformByContext ctx) {
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
