/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import io.proleap.cobol.CobolParser.ReportDescriptionHeadingClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.report.HeadingClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class HeadingClauseImpl extends CobolDivisionElementImpl implements HeadingClause {

	protected final ReportDescriptionHeadingClauseContext ctx;

	protected IntegerLiteral headingIntegerLiteral;

	public HeadingClauseImpl(final ProgramUnit programUnit, final ReportDescriptionHeadingClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getHeadingIntegerLiteral() {
		return headingIntegerLiteral;
	}

	@Override
	public void setHeadingIntegerLiteral(final IntegerLiteral headingIntegerLiteral) {
		this.headingIntegerLiteral = headingIntegerLiteral;
	}

}
