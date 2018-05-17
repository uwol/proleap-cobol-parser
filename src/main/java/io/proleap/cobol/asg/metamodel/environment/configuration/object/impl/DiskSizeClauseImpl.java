/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.configuration.object.impl;

import io.proleap.cobol.CobolParser.DiskSizeClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.DiskSizeClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class DiskSizeClauseImpl extends CobolDivisionElementImpl implements DiskSizeClause {

	protected final DiskSizeClauseContext ctx;

	protected Unit unit;

	protected ValueStmt valueStmt;

	public DiskSizeClauseImpl(final ProgramUnit programUnit, final DiskSizeClauseContext ctx) {
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
