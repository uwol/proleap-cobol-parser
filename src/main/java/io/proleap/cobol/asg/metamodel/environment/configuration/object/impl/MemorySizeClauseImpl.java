/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.configuration.object.impl;

import io.proleap.cobol.CobolParser.MemorySizeClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.MemorySizeClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class MemorySizeClauseImpl extends CobolDivisionElementImpl implements MemorySizeClause {

	protected final MemorySizeClauseContext ctx;

	protected Unit unit;

	protected ValueStmt valueStmt;

	public MemorySizeClauseImpl(final ProgramUnit programUnit, final MemorySizeClauseContext ctx) {
		super(programUnit, ctx);

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
