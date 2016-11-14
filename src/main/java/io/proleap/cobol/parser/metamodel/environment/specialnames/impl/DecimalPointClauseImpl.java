/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.specialnames.impl;

import io.proleap.cobol.Cobol85Parser.DecimalPointClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.specialnames.DecimalPointClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class DecimalPointClauseImpl extends CobolDivisionElementImpl implements DecimalPointClause {

	protected final DecimalPointClauseContext ctx;

	public DecimalPointClauseImpl(final ProgramUnit programUnit, final DecimalPointClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
