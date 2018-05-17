/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionFullClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.FullClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class FullClauseImpl extends CobolDivisionElementImpl implements FullClause {

	protected ScreenDescriptionFullClauseContext ctx;

	protected FullClauseType fullClauseType;

	public FullClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionFullClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public FullClauseType getFullClauseType() {
		return fullClauseType;
	}

	@Override
	public void setFullClauseType(final FullClauseType fullClauseType) {
		this.fullClauseType = fullClauseType;
	}

}
