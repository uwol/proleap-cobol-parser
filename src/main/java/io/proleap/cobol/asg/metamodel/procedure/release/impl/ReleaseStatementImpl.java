/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.release.impl;

import io.proleap.cobol.CobolParser.ReleaseStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.release.ReleaseStatement;

public class ReleaseStatementImpl extends StatementImpl implements ReleaseStatement {

	protected Call contentCall;

	protected final ReleaseStatementContext ctx;

	protected Call recordCall;

	protected final StatementType statementType = StatementTypeEnum.RELEASE;

	public ReleaseStatementImpl(final ProgramUnit programUnit, final Scope scope, final ReleaseStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getContentCall() {
		return contentCall;
	}

	@Override
	public Call getRecordCall() {
		return recordCall;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setContentCall(final Call contentCall) {
		this.contentCall = contentCall;
	}

	@Override
	public void setRecordCall(final Call recordCall) {
		this.recordCall = recordCall;
	}

}
