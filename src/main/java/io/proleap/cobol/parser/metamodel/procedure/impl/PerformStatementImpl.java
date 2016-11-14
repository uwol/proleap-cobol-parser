/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.impl;

import io.proleap.cobol.Cobol85Parser.PerformStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.PerformProcedureStatement;
import io.proleap.cobol.parser.metamodel.procedure.PerformStatement;

public class PerformStatementImpl extends CobolDivisionElementImpl implements PerformStatement {

	protected final PerformStatementContext ctx;

	protected PerformProcedureStatement performProcedureStatement;

	public PerformStatementImpl(final ProgramUnit programUnit, final PerformStatementContext ctx) {
		super(programUnit, ctx);

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
