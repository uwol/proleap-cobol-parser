/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.subtract.impl;

import io.proleap.cobol.Cobol85Parser.SubtractSubtrahendContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.subtract.Subtrahend;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class SubtrahendImpl extends CobolDivisionElementImpl implements Subtrahend {

	protected final SubtractSubtrahendContext ctx;

	protected ValueStmt subtrahendValueStmt;

	public SubtrahendImpl(final ProgramUnit programUnit, final SubtractSubtrahendContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getSubtrahendValueStmt() {
		return subtrahendValueStmt;
	}

	@Override
	public void setSubtrahendValueStmt(final ValueStmt subtrahendValueStmt) {
		this.subtrahendValueStmt = subtrahendValueStmt;
	}

}
