/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import io.proleap.cobol.CobolParser.ReportGroupNextGroupClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.report.NextGroupClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class NextGroupClauseImpl extends CobolDivisionElementImpl implements NextGroupClause {

	protected ReportGroupNextGroupClauseContext ctx;

	protected IntegerLiteral integerLiteral;

	protected NextGroupClauseType nextGroupClauseType;

	public NextGroupClauseImpl(final ProgramUnit programUnit, final ReportGroupNextGroupClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getIntegerLiteral() {
		return integerLiteral;
	}

	@Override
	public NextGroupClauseType getNextGroupClauseType() {
		return nextGroupClauseType;
	}

	@Override
	public void setIntegerLiteral(final IntegerLiteral integerLiteral) {
		this.integerLiteral = integerLiteral;
	}

	@Override
	public void setNextGroupClauseType(final NextGroupClauseType nextGroupClauseType) {
		this.nextGroupClauseType = nextGroupClauseType;
	}

}
