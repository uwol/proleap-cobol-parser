/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import io.proleap.cobol.Cobol85Parser.PerformStatementContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.PerformProcedureStatement;
import io.proleap.cobol.parser.metamodel.PerformStatement;

public class PerformStatementImpl extends CobolScopedElementImpl implements PerformStatement {

	protected final PerformStatementContext ctx;

	protected PerformProcedureStatement performProcedureStatement;

	public PerformStatementImpl(final CopyBook copyBook, final CobolScope superScope,
			final PerformStatementContext ctx) {
		super(copyBook, superScope, ctx);

		this.ctx = ctx;
	}

	@Override
	public PerformProcedureStatement getPerformProcedureStatement() {
		return performProcedureStatement;
	}

	@Override
	public void setPerformProcedureStatement(final PerformProcedureStatement performProcedureStatement) {
		this.performProcedureStatement = performProcedureStatement;
	}

}
