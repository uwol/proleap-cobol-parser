/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.ReportDescriptionFirstDetailClauseContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.FirstDetailClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class FirstDetailClauseImpl extends CobolDivisionElementImpl implements FirstDetailClause {

	protected final ReportDescriptionFirstDetailClauseContext ctx;

	protected IntegerLiteral firstDetailIntegerLiteral;

	public FirstDetailClauseImpl(final ProgramUnit programUnit, final ReportDescriptionFirstDetailClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getFirstDetailIntegerLiteral() {
		return firstDetailIntegerLiteral;
	}

	@Override
	public void setFirstDetailIntegerLiteral(final IntegerLiteral firstDetailIntegerLiteral) {
		this.firstDetailIntegerLiteral = firstDetailIntegerLiteral;
	}

}
