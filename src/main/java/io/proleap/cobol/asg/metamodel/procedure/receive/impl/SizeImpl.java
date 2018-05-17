/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive.impl;

import io.proleap.cobol.CobolParser.ReceiveSizeContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.receive.Size;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class SizeImpl extends CobolDivisionElementImpl implements Size {

	protected final ReceiveSizeContext ctx;

	protected ValueStmt sizeValueStmt;

	public SizeImpl(final ProgramUnit programUnit, final ReceiveSizeContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getSizeValueStmt() {
		return sizeValueStmt;
	}

	@Override
	public void setSizeValueStmt(final ValueStmt sizeValueStmt) {
		this.sizeValueStmt = sizeValueStmt;
	}

}
