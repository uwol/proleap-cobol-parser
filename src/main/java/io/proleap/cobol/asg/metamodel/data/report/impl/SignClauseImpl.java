/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import io.proleap.cobol.CobolParser.ReportGroupSignClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.report.SignClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class SignClauseImpl extends CobolDivisionElementImpl implements SignClause {

	protected ReportGroupSignClauseContext ctx;

	protected boolean separate;

	protected SignClauseType signClauseType;

	public SignClauseImpl(final ProgramUnit programUnit, final ReportGroupSignClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public SignClauseType getSignClauseType() {
		return signClauseType;
	}

	@Override
	public boolean isSeparate() {
		return separate;
	}

	@Override
	public void setSeparate(final boolean separate) {
		this.separate = separate;
	}

	@Override
	public void setSignClauseType(final SignClauseType signClauseType) {
		this.signClauseType = signClauseType;
	}

}
