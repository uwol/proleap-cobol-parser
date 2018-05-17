/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.stop.impl;

import io.proleap.cobol.CobolParser.StopStatementContext;
import io.proleap.cobol.CobolParser.StopStatementGivingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatementGiving;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class StopStatementImpl extends StatementImpl implements StopStatement {

	protected final StopStatementContext ctx;

	protected ValueStmt displayValueStmt;

	protected final StatementType statementType = StatementTypeEnum.STOP;

	protected StopStatementGiving stopStatementGiving;

	protected StopType stopType;

	public StopStatementImpl(final ProgramUnit programUnit, final Scope scope, final StopStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public StopStatementGiving addStopStatementGiving(final StopStatementGivingContext ctx) {
		StopStatementGiving result = (StopStatementGiving) getASGElement(ctx);

		if (result == null) {
			result = new StopStatementGivingImpl(programUnit, ctx);

			final ValueStmt givingValueStmt = createValueStmt(ctx.identifier(), ctx.integerLiteral());
			result.setGivingValueStmt(givingValueStmt);

			stopStatementGiving = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt getDisplayValueStmt() {
		return displayValueStmt;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public StopStatementGiving getStopStatementGiving() {
		return stopStatementGiving;
	}

	@Override
	public StopType getStopType() {
		return stopType;
	}

	@Override
	public void setDisplayValueStmt(final ValueStmt displayValueStmt) {
		this.displayValueStmt = displayValueStmt;
	}

	@Override
	public void setStopType(final StopType stopType) {
		this.stopType = stopType;
	}
}
