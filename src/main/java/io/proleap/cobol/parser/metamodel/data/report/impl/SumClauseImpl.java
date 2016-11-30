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
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.data.report.SumClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

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
