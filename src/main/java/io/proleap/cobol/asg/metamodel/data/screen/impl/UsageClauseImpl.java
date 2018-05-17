/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionUsageClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.UsageClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class UsageClauseImpl extends CobolDivisionElementImpl implements UsageClause {

	protected ScreenDescriptionUsageClauseContext ctx;

	protected UsageClauseType usageClauseType;

	public UsageClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionUsageClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public UsageClauseType getUsageClauseType() {
		return usageClauseType;
	}

	@Override
	public void setUsageClauseType(final UsageClauseType usageClauseType) {
		this.usageClauseType = usageClauseType;
	}

}
