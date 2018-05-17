/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file.impl;

import io.proleap.cobol.CobolParser.BlockContainsClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.file.BlockContainsClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class BlockContainsClauseImpl extends CobolDivisionElementImpl implements BlockContainsClause {

	protected final BlockContainsClauseContext ctx;

	protected IntegerLiteral from;

	protected IntegerLiteral to;

	protected Unit unit;

	public BlockContainsClauseImpl(final ProgramUnit programUnit, final BlockContainsClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getFrom() {
		return from;
	}

	@Override
	public IntegerLiteral getTo() {
		return to;
	}

	@Override
	public Unit getUnit() {
		return unit;
	}

	@Override
	public void setFrom(final IntegerLiteral from) {
		this.from = from;
	}

	@Override
	public void setTo(final IntegerLiteral to) {
		this.to = to;
	}

	@Override
	public void setUnit(final Unit unit) {
		this.unit = unit;
	}
}
