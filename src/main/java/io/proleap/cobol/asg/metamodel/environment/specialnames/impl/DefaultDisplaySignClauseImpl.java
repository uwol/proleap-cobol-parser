/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames.impl;

import io.proleap.cobol.Cobol85Parser.DefaultDisplaySignClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.specialnames.DefaultDisplaySignClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class DefaultDisplaySignClauseImpl extends CobolDivisionElementImpl implements DefaultDisplaySignClause {

	protected final DefaultDisplaySignClauseContext ctx;

	protected DefaultDisplaySignClauseType defaultDisplaySignClauseType;

	public DefaultDisplaySignClauseImpl(final ProgramUnit programUnit, final DefaultDisplaySignClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DefaultDisplaySignClauseType getDefaultDisplaySignClauseType() {
		return defaultDisplaySignClauseType;
	}

	@Override
	public void setDefaultDisplaySignClauseType(final DefaultDisplaySignClauseType defaultDisplaySignClauseType) {
		this.defaultDisplaySignClauseType = defaultDisplaySignClauseType;
	}

}
