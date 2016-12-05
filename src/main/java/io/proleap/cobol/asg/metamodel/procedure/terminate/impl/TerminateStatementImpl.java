/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.terminate.impl;

import io.proleap.cobol.Cobol85Parser.TerminateStatementContext;
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

	protected final StatementType statementType = StatementTypeEnum.Terminate;

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
