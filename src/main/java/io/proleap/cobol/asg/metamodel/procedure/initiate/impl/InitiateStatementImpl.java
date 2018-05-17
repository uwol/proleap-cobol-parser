/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.initiate.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.InitiateStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.initiate.InitiateStatement;

public class InitiateStatementImpl extends StatementImpl implements InitiateStatement {

	protected final InitiateStatementContext ctx;

	protected List<Call> reportCalls = new ArrayList<Call>();

	protected final StatementType statementType = StatementTypeEnum.INITIATE;

	public InitiateStatementImpl(final ProgramUnit programUnit, final Scope scope, final InitiateStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addReportCall(final Call reportCall) {
		reportCalls.add(reportCall);
	}

	@Override
	public List<Call> getReportCalls() {
		return reportCalls;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

}
