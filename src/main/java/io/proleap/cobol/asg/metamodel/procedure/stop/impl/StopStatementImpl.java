/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.stop.impl;

import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class StopStatementImpl extends StatementImpl implements StopStatement {

	protected final StopStatementContext ctx;

	protected ValueStmt displayValueStmt;

	protected final StatementType statementType = StatementTypeEnum.STOP;

	protected Type type;

	public StopStatementImpl(final ProgramUnit programUnit, final Scope scope, final StopStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
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
	public Type getType() {
		return type;
	}

	@Override
	public void setDisplayValueStmt(final ValueStmt displayValueStmt) {
		this.displayValueStmt = displayValueStmt;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
