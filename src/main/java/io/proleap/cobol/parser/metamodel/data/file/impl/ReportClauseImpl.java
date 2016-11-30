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
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.data.file.ReportClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class ReportClauseImpl extends CobolDivisionElementImpl implements ReportClause {

	protected final ReportClauseContext ctx;

	protected List<Call> reportCalls = new ArrayList<Call>();

	public ReportClauseImpl(final ProgramUnit programUnit, final ReportClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addReportCall(final Call dataCall) {
		reportCalls.add(dataCall);
	}

	@Override
	public List<Call> getReportCalls() {
		return reportCalls;
	}

}
