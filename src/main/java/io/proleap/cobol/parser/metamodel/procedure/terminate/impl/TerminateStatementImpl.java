/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.terminate.impl;

import io.proleap.cobol.Cobol85Parser.TerminateStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.terminate.TerminateStatement;

public class TerminateStatementImpl extends StatementImpl implements TerminateStatement {

	protected final TerminateStatementContext ctx;

	protected Call reportCall;

	public TerminateStatementImpl(final ProgramUnit programUnit, final TerminateStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getReportCall() {
		return reportCall;
	}

	@Override
	public void setReportCall(final Call reportCall) {
		this.reportCall = reportCall;
	}

}
