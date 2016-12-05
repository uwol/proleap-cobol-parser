/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.ReportDescriptionPageLimitClauseContext;
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
