/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.ReportClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.file.ReportClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

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
