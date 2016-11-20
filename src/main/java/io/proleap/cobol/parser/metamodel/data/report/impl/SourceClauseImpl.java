/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.ReportGroupSourceClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.SourceClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class SourceClauseImpl extends CobolDivisionElementImpl implements SourceClause {

	protected ReportGroupSourceClauseContext ctx;

	protected ValueStmt sourceValueStmt;

	public SourceClauseImpl(final ProgramUnit programUnit, final ReportGroupSourceClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getSourceValueStmt() {
		return sourceValueStmt;
	}

	@Override
	public void setSourceValueStmt(final ValueStmt sourceValueStmt) {
		this.sourceValueStmt = sourceValueStmt;
	}

}
