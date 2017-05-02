/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import io.proleap.cobol.Cobol85Parser.PerformVaryingClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.VaryingClause;

public class VaryingClauseImpl extends CobolDivisionElementImpl implements VaryingClause {

	protected final PerformVaryingClauseContext ctx;

	public VaryingClauseImpl(final ProgramUnit programUnit, final PerformVaryingClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
