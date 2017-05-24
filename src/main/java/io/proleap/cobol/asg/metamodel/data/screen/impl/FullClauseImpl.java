/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionFullClauseContext;
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
