/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import io.proleap.cobol.CobolParser.ReportGroupColumnNumberClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.report.ColumnNumberClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

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
