/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionBlankWhenZeroClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.BlankWhenZeroClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class BlankWhenZeroClauseImpl extends CobolDivisionElementImpl implements BlankWhenZeroClause {

	protected boolean blankWhenZero;

	protected ScreenDescriptionBlankWhenZeroClauseContext ctx;

	public BlankWhenZeroClauseImpl(final ProgramUnit programUnit,
			final ScreenDescriptionBlankWhenZeroClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isBlankWhenZero() {
		return blankWhenZero;
	}

	@Override
	public void setBlankWhenZero(final boolean blankWhenZero) {
		this.blankWhenZero = blankWhenZero;
	}
}
