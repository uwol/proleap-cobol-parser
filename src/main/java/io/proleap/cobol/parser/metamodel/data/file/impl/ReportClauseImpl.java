/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.ReportClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.file.ReportClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class ReportClauseImpl extends CobolDivisionElementImpl implements ReportClause {

	protected final ReportClauseContext ctx;

	protected List<ValueStmt> reportNameValueStmts = new ArrayList<ValueStmt>();

	public ReportClauseImpl(final ProgramUnit programUnit, final ReportClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addReportNameValueStmt(final ValueStmt dataNameValueStmt) {
		reportNameValueStmts.add(dataNameValueStmt);
	}

	@Override
	public List<ValueStmt> getReportNameValueStmts() {
		return reportNameValueStmts;
	}

}
