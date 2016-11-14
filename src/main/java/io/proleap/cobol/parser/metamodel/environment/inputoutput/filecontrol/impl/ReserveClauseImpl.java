/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.Cobol85Parser.ReserveClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.ReserveClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.IntegerLiteralValueStmt;

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
