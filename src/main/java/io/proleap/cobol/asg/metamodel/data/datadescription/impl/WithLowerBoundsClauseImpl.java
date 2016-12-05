/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataWithLowerBoundsClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.WithLowerBoundsClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class WithLowerBoundsClauseImpl extends CobolDivisionElementImpl implements WithLowerBoundsClause {

	protected DataWithLowerBoundsClauseContext ctx;

	protected boolean withLowerBounds;

	public WithLowerBoundsClauseImpl(final ProgramUnit programUnit, final DataWithLowerBoundsClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isWithLowerBounds() {
		return withLowerBounds;
	}

	@Override
	public void setWithLowerBounds(final boolean withLowerBounds) {
		this.withLowerBounds = withLowerBounds;
	}

}
