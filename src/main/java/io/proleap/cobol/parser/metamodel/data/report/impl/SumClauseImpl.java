/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.ReportGroupSumClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.SumClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class SumClauseImpl extends CobolDivisionElementImpl implements SumClause {

	protected ReportGroupSumClauseContext ctx;

	protected List<ValueStmt> sumValueStmts = new ArrayList<ValueStmt>();

	protected List<ValueStmt> uponValueStmts = new ArrayList<ValueStmt>();

	public SumClauseImpl(final ProgramUnit programUnit, final ReportGroupSumClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addSumValueStmt(final ValueStmt sumValueStmt) {
		sumValueStmts.add(sumValueStmt);
	}

	@Override
	public void addUponValueStmt(final ValueStmt uponValueStmt) {
		uponValueStmts.add(uponValueStmt);
	}

	@Override
	public List<ValueStmt> getSumValueStmts() {
		return sumValueStmts;
	}

	@Override
	public List<ValueStmt> getUponValueStmts() {
		return uponValueStmts;
	}

}
