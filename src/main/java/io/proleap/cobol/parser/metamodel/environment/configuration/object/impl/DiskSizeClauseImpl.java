/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.configuration.object.impl;

import io.proleap.cobol.Cobol85Parser.DiskSizeClauseContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.configuration.object.DiskSizeClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class DiskSizeClauseImpl extends CobolDivisionElementImpl implements DiskSizeClause {

	protected final DiskSizeClauseContext ctx;

	protected Unit unit;

	protected ValueStmt valueStmt;

	public DiskSizeClauseImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final DiskSizeClauseContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Unit getUnit() {
		return unit;
	}

	@Override
	public ValueStmt getValueStmt() {
		return valueStmt;
	}

	@Override
	public void setUnit(final Unit unit) {
		this.unit = unit;
	}

	@Override
	public void setValueStmt(final ValueStmt valueStmt) {
		this.valueStmt = valueStmt;
	}

}
