/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.ReportGroupNextGroupClauseContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.NextGroupClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class NextGroupClauseImpl extends CobolDivisionElementImpl implements NextGroupClause {

	protected ReportGroupNextGroupClauseContext ctx;

	protected IntegerLiteral integerLiteral;

	protected Type type;

	public NextGroupClauseImpl(final ProgramUnit programUnit, final ReportGroupNextGroupClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getIntegerLiteral() {
		return integerLiteral;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setIntegerLiteral(final IntegerLiteral integerLiteral) {
		this.integerLiteral = integerLiteral;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
