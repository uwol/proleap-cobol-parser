/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import io.proleap.cobol.CobolParser.ReportDescriptionPageLimitClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.report.PageLimitClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class PageLimitClauseImpl extends CobolDivisionElementImpl implements PageLimitClause {

	protected final ReportDescriptionPageLimitClauseContext ctx;

	protected IntegerLiteral pageLimitIntegerLiteral;

	public PageLimitClauseImpl(final ProgramUnit programUnit, final ReportDescriptionPageLimitClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getPageLimitIntegerLiteral() {
		return pageLimitIntegerLiteral;
	}

	@Override
	public void setPageLimitIntegerLiteral(final IntegerLiteral pageLimitIntegerLiteral) {
		this.pageLimitIntegerLiteral = pageLimitIntegerLiteral;
	}

}
