/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionAutoClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.AutoClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class AutoClauseImpl extends CobolDivisionElementImpl implements AutoClause {

	protected AutoClauseType autoClauseType;

	protected ScreenDescriptionAutoClauseContext ctx;

	public AutoClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionAutoClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AutoClauseType getAutoClauseType() {
		return autoClauseType;
	}

	@Override
	public void setAutoClauseType(final AutoClauseType autoClauseType) {
		this.autoClauseType = autoClauseType;
	}

}
