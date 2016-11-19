/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.ReportDescriptionFootingClauseContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.FootingClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

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
