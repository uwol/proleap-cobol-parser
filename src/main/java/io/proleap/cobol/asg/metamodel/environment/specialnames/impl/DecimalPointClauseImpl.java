/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames.impl;

import io.proleap.cobol.CobolParser.DecimalPointClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.specialnames.DecimalPointClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class DecimalPointClauseImpl extends CobolDivisionElementImpl implements DecimalPointClause {

	protected final DecimalPointClauseContext ctx;

	public DecimalPointClauseImpl(final ProgramUnit programUnit, final DecimalPointClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
