/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.ReportGroupResetClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.ResetClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class ResetClauseImpl extends CobolDivisionElementImpl implements ResetClause {

	protected ReportGroupResetClauseContext ctx;

	protected ValueStmt dataValueStmt;

	public ResetClauseImpl(final ProgramUnit programUnit, final ReportGroupResetClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getDataValueStmt() {
		return dataValueStmt;
	}

	@Override
	public void setDataValueStmt(final ValueStmt dataValueStmt) {
		this.dataValueStmt = dataValueStmt;
	}

}
