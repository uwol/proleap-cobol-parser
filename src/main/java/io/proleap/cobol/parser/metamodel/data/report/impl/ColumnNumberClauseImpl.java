/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.ReportGroupColumnNumberClauseContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.ColumnNumberClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class ColumnNumberClauseImpl extends CobolDivisionElementImpl implements ColumnNumberClause {

	protected ReportGroupColumnNumberClauseContext ctx;

	protected IntegerLiteral integerLiteral;

	public ColumnNumberClauseImpl(final ProgramUnit programUnit, final ReportGroupColumnNumberClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getIntegerLiteral() {
		return integerLiteral;
	}

	@Override
	public void setIntegerLiteral(final IntegerLiteral integerLiteral) {
		this.integerLiteral = integerLiteral;
	}

}
