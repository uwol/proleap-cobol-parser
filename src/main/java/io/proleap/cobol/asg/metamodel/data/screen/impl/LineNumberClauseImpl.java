/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionLineClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.LineNumberClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class LineNumberClauseImpl extends CobolDivisionElementImpl implements LineNumberClause {

	protected ScreenDescriptionLineClauseContext ctx;

	protected IntegerLiteral integerLiteral;

	protected LineNumberClauseType lineNumberClauseType;

	public LineNumberClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionLineClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getIntegerLiteral() {
		return integerLiteral;
	}

	@Override
	public LineNumberClauseType getLineNumberClauseType() {
		return lineNumberClauseType;
	}

	@Override
	public void setIntegerLiteral(final IntegerLiteral integerLiteral) {
		this.integerLiteral = integerLiteral;
	}

	@Override
	public void setLineNumberClauseType(final LineNumberClauseType lineNumberClauseType) {
		this.lineNumberClauseType = lineNumberClauseType;
	}

}
