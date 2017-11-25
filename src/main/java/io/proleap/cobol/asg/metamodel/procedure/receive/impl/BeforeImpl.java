/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive.impl;

import io.proleap.cobol.Cobol85Parser.ReceiveBeforeContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.receive.Before;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class BeforeImpl extends CobolDivisionElementImpl implements Before {

	protected final ReceiveBeforeContext ctx;

	protected ValueStmt timeValueStmt;

	public BeforeImpl(final ProgramUnit programUnit, final ReceiveBeforeContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getTimeValueStmt() {
		return timeValueStmt;
	}

	@Override
	public void setTimeValueStmt(final ValueStmt timeValueStmt) {
		this.timeValueStmt = timeValueStmt;
	}
}
