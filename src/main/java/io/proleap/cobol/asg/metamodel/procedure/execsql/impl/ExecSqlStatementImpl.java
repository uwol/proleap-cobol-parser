/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.execsql.impl;

import io.proleap.cobol.CobolParser.ExecSqlStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.execsql.ExecSqlStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class ExecSqlStatementImpl extends StatementImpl implements ExecSqlStatement {

	protected final ExecSqlStatementContext ctx;

	protected String execSqlText;

	protected final StatementType statementType = StatementTypeEnum.EXEC_SQL;

	public ExecSqlStatementImpl(final ProgramUnit programUnit, final Scope scope, final ExecSqlStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public String getExecSqlText() {
		return execSqlText;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setExecSqlText(final String execSqlText) {
		this.execSqlText = execSqlText;
	}

}
