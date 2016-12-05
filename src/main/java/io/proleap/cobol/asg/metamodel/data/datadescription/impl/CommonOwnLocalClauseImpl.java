/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataCommonOwnLocalClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.CommonOwnLocalClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class CommonOwnLocalClauseImpl extends CobolDivisionElementImpl implements CommonOwnLocalClause {

	protected DataCommonOwnLocalClauseContext ctx;

	protected Invariance invariance;

	public CommonOwnLocalClauseImpl(final ProgramUnit programUnit, final DataCommonOwnLocalClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Invariance getInvariance() {
		return invariance;
	}

	@Override
	public void setInvariance(final Invariance invariance) {
		this.invariance = invariance;
	}

}
