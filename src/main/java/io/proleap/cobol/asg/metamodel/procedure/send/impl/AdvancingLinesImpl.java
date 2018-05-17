/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.send.impl;

import io.proleap.cobol.CobolParser.SendAdvancingLinesContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.send.AdvancingLines;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class AdvancingLinesImpl extends CobolDivisionElementImpl implements AdvancingLines {

	protected SendAdvancingLinesContext ctx;

	protected ValueStmt linesValueStmt;

	public AdvancingLinesImpl(final ProgramUnit programUnit, final SendAdvancingLinesContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getLinesValueStmt() {
		return linesValueStmt;
	}

	@Override
	public void setLinesValueStmt(final ValueStmt linesValueStmt) {
		this.linesValueStmt = linesValueStmt;
	}

}
