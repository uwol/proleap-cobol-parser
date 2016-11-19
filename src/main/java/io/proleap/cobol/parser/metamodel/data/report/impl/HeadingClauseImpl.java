/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.ReportDescriptionHeadingClauseContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.HeadingClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

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
