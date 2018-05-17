/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import io.proleap.cobol.CobolParser.ReportGroupSourceClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.report.SourceClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class SourceClauseImpl extends CobolDivisionElementImpl implements SourceClause {

	protected ReportGroupSourceClauseContext ctx;

	protected Call sourceCall;

	public SourceClauseImpl(final ProgramUnit programUnit, final ReportGroupSourceClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getSourceCall() {
		return sourceCall;
	}

	@Override
	public void setSourceCall(final Call sourceCall) {
		this.sourceCall = sourceCall;
	}
}
