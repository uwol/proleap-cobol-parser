/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataUsingClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.UsingClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class UsingClauseImpl extends CobolDivisionElementImpl implements UsingClause {

	protected DataUsingClauseContext ctx;

	protected ValueStmt ofValueStmt;

	protected UsingClauseType usingClauseType;

	public UsingClauseImpl(final ProgramUnit programUnit, final DataUsingClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getOfValueStmt() {
		return ofValueStmt;
	}

	@Override
	public UsingClauseType getUsingClauseType() {
		return usingClauseType;
	}

	@Override
	public void setOfValueStmt(final ValueStmt ofValueStmt) {
		this.ofValueStmt = ofValueStmt;
	}

	@Override
	public void setUsingClauseType(final UsingClauseType usingClauseType) {
		this.usingClauseType = usingClauseType;
	}

}
