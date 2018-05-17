/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.CobolParser.DataExternalClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.ExternalClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;

public class ExternalClauseImpl extends CobolDivisionElementImpl implements ExternalClause {

	protected LiteralValueStmt byLiteralValueStmt;

	protected DataExternalClauseContext ctx;

	protected boolean external;

	public ExternalClauseImpl(final ProgramUnit programUnit, final DataExternalClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public LiteralValueStmt getByLiteralValueStmt() {
		return byLiteralValueStmt;
	}

	@Override
	public boolean isExternal() {
		return external;
	}

	@Override
	public void setByLiteralValueStmt(final LiteralValueStmt byLiteralValueStmt) {
		this.byLiteralValueStmt = byLiteralValueStmt;
	}

	@Override
	public void setExternal(final boolean external) {
		this.external = external;
	}
}
