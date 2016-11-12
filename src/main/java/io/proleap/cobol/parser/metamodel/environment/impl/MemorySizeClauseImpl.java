/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.impl;

import io.proleap.cobol.Cobol85Parser.MemorySizeClauseContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.MemorySizeClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class MemorySizeClauseImpl extends CobolDivisionElementImpl implements MemorySizeClause {

	protected final MemorySizeClauseContext ctx;

	protected Unit unit;

	public MemorySizeClauseImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final MemorySizeClauseContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Unit getUnit() {
		return unit;
	}

	@Override
	public void setUnit(final Unit unit) {
		this.unit = unit;
	}

}
