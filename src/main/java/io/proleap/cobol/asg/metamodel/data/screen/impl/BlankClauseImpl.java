/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionBlankClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.BlankClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class BlankClauseImpl extends CobolDivisionElementImpl implements BlankClause {

	protected BlankClauseType blankClauseType;

	protected ScreenDescriptionBlankClauseContext ctx;

	public BlankClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionBlankClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public BlankClauseType getBlankClauseType() {
		return blankClauseType;
	}

	@Override
	public void setBlankClauseType(final BlankClauseType blankClauseType) {
		this.blankClauseType = blankClauseType;
	}

}
