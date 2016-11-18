/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataExternalClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.ExternalClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.LiteralValueStmt;

public class ExternalClauseImpl extends CobolDivisionElementImpl implements ExternalClause {

	protected LiteralValueStmt byLiteralValueStmt;

	protected DataExternalClauseContext ctx;

	protected Boolean external;

	public ExternalClauseImpl(final ProgramUnit programUnit, final DataExternalClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public LiteralValueStmt getByLiteralValueStmt() {
		return byLiteralValueStmt;
	}

	@Override
	public Boolean isExternal() {
		return external;
	}

	@Override
	public void setByLiteralValueStmt(final LiteralValueStmt byLiteralValueStmt) {
		this.byLiteralValueStmt = byLiteralValueStmt;
	}

	@Override
	public void setExternal(final Boolean external) {
		this.external = external;
	}
}
