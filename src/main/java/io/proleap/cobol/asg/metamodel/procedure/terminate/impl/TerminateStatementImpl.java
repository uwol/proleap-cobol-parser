/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.terminate.impl;

import io.proleap.cobol.CobolParser.TerminateStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.terminate.TerminateStatement;

public class TerminateStatementImpl extends StatementImpl implements TerminateStatement {

	protected final TerminateStatementContext ctx;

	protected Call reportCall;

	protected final StatementType statementType = StatementTypeEnum.TERMINATE;

	public TerminateStatementImpl(final ProgramUnit programUnit, final Scope scope,
			final TerminateStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getReportCall() {
		return reportCall;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setReportCall(final Call reportCall) {
		this.reportCall = reportCall;
	}

}
