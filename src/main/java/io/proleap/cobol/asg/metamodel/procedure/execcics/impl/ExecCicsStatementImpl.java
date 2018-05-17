/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.execcics.impl;

import io.proleap.cobol.CobolParser.ExecCicsStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.execcics.ExecCicsStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class ExecCicsStatementImpl extends StatementImpl implements ExecCicsStatement {

	protected final ExecCicsStatementContext ctx;

	protected String execCicsText;

	protected final StatementType statementType = StatementTypeEnum.EXEC_CICS;

	public ExecCicsStatementImpl(final ProgramUnit programUnit, final Scope scope, final ExecCicsStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public String getExecCicsText() {
		return execCicsText;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setExecCicsText(final String execCicsText) {
		this.execCicsText = execCicsText;
	}

}
