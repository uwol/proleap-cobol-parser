/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionBlankClauseContext;
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
