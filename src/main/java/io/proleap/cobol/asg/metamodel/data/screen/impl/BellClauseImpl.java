/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionBellClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.BellClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class BellClauseImpl extends CobolDivisionElementImpl implements BellClause {

	protected BellClauseType bellClauseType;

	protected ScreenDescriptionBellClauseContext ctx;

	public BellClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionBellClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public BellClauseType getBellClauseType() {
		return bellClauseType;
	}

	@Override
	public void setBellClauseType(final BellClauseType bellClauseType) {
		this.bellClauseType = bellClauseType;
	}

}
