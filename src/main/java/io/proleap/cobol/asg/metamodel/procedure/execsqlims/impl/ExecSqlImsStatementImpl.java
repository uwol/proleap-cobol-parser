/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.execsqlims.impl;

import io.proleap.cobol.CobolParser.ExecSqlImsStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.execsqlims.ExecSqlImsStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class ExecSqlImsStatementImpl extends StatementImpl implements ExecSqlImsStatement {

	protected final ExecSqlImsStatementContext ctx;

	protected String execSqlImsText;

	protected final StatementType statementType = StatementTypeEnum.EXEC_SQLIMS;

	public ExecSqlImsStatementImpl(final ProgramUnit programUnit, final Scope scope,
			final ExecSqlImsStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public String getExecSqlImsText() {
		return execSqlImsText;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setExecSqlImsText(final String execSqlImsText) {
		this.execSqlImsText = execSqlImsText;
	}

}
