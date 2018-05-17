/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.generate.impl;

import io.proleap.cobol.CobolParser.GenerateStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.generate.GenerateStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class GenerateStatementImpl extends StatementImpl implements GenerateStatement {

	protected final GenerateStatementContext ctx;

	protected Call reportDescriptionCall;

	protected final StatementType statementType = StatementTypeEnum.GENERATE;

	public GenerateStatementImpl(final ProgramUnit programUnit, final Scope scope, final GenerateStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getReportDescriptionCall() {
		return reportDescriptionCall;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setReportDescriptionCall(final Call reportDescriptionCall) {
		this.reportDescriptionCall = reportDescriptionCall;
	}
}
