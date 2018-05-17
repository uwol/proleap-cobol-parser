/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import io.proleap.cobol.CobolParser.ReportDescriptionFootingClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.report.FootingClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class FootingClauseImpl extends CobolDivisionElementImpl implements FootingClause {

	protected final ReportDescriptionFootingClauseContext ctx;

	protected IntegerLiteral footingIntegerLiteral;

	public FootingClauseImpl(final ProgramUnit programUnit, final ReportDescriptionFootingClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getFootingIntegerLiteral() {
		return footingIntegerLiteral;
	}

	@Override
	public void setFootingIntegerLiteral(final IntegerLiteral footingIntegerLiteral) {
		this.footingIntegerLiteral = footingIntegerLiteral;
	}

}
