/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.generate.impl;

import io.proleap.cobol.Cobol85Parser.GenerateStatementContext;
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

	protected final StatementType statementType = StatementTypeEnum.Generate;

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
