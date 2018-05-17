/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionRequiredClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.RequiredClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class RequiredClauseImpl extends CobolDivisionElementImpl implements RequiredClause {

	protected ScreenDescriptionRequiredClauseContext ctx;

	protected RequiredClauseType requiredClauseType;

	public RequiredClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionRequiredClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public RequiredClauseType getRequiredClauseType() {
		return requiredClauseType;
	}

	@Override
	public void setRequiredClauseType(final RequiredClauseType requiredClauseType) {
		this.requiredClauseType = requiredClauseType;
	}

}
