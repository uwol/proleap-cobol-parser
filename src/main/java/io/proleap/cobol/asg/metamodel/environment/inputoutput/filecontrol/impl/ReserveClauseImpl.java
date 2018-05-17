/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.CobolParser.ReserveClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.ReserveClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;

public class ReserveClauseImpl extends CobolDivisionElementImpl implements ReserveClause {

	protected final ReserveClauseContext ctx;

	protected IntegerLiteralValueStmt valueStmt;

	public ReserveClauseImpl(final ProgramUnit programUnit, final ReserveClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteralValueStmt getValueStmt() {
		return valueStmt;
	}

	@Override
	public void setValueStmt(final IntegerLiteralValueStmt valueStmt) {
		this.valueStmt = valueStmt;
	}

}
