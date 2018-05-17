/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.ReportGroupSumClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.report.SumClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class SumClauseImpl extends CobolDivisionElementImpl implements SumClause {

	protected ReportGroupSumClauseContext ctx;

	protected List<Call> sumValueCalls = new ArrayList<Call>();

	protected List<Call> uponCalls = new ArrayList<Call>();

	public SumClauseImpl(final ProgramUnit programUnit, final ReportGroupSumClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addSumCall(final Call sumCall) {
		sumValueCalls.add(sumCall);
	}

	@Override
	public void addUponCall(final Call uponCall) {
		uponCalls.add(uponCall);
	}

	@Override
	public List<Call> getSumCalls() {
		return sumValueCalls;
	}

	@Override
	public List<Call> getUponCalls() {
		return uponCalls;
	}

}
