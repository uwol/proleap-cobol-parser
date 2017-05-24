/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionColumnClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.ColumnNumberClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ColumnNumberClauseImpl extends CobolDivisionElementImpl implements ColumnNumberClause {

	protected ColumnNumberClauseType columnNumberClauseType;

	protected ScreenDescriptionColumnClauseContext ctx;

	protected IntegerLiteral integerLiteral;

	public ColumnNumberClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionColumnClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ColumnNumberClauseType getColumnNumberClauseType() {
		return columnNumberClauseType;
	}

	@Override
	public IntegerLiteral getIntegerLiteral() {
		return integerLiteral;
	}

	@Override
	public void setColumnNumberClauseType(final ColumnNumberClauseType columnNumberClauseType) {
		this.columnNumberClauseType = columnNumberClauseType;
	}

	@Override
	public void setIntegerLiteral(final IntegerLiteral integerLiteral) {
		this.integerLiteral = integerLiteral;
	}

}
