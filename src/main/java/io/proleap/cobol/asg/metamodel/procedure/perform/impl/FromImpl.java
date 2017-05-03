/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import io.proleap.cobol.Cobol85Parser.PerformFromContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.From;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class FromImpl extends CobolDivisionElementImpl implements From {

	protected final PerformFromContext ctx;

	protected ValueStmt fromValueStmt;

	public FromImpl(final ProgramUnit programUnit, final PerformFromContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getFromValueStmt() {
		return fromValueStmt;
	}

	@Override
	public void setFromValueStmt(final ValueStmt fromValueStmt) {
		this.fromValueStmt = fromValueStmt;
	}

}
