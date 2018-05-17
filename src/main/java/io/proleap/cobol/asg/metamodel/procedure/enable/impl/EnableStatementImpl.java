/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.enable.impl;

import io.proleap.cobol.CobolParser.EnableStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.enable.EnableStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class EnableStatementImpl extends StatementImpl implements EnableStatement {

	protected Call communicationDescriptionCall;

	protected final EnableStatementContext ctx;

	protected EnableType enableType;

	protected ValueStmt keyValueStmt;

	protected final StatementType statementType = StatementTypeEnum.ENABLE;

	protected boolean terminal;

	public EnableStatementImpl(final ProgramUnit programUnit, final Scope scope, final EnableStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getCommunicationDescriptionCall() {
		return communicationDescriptionCall;
	}

	@Override
	public EnableType getEnableType() {
		return enableType;
	}

	@Override
	public ValueStmt getKeyValueStmt() {
		return keyValueStmt;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public boolean isTerminal() {
		return terminal;
	}

	@Override
	public void setCommunicationDescriptionCall(final Call communicationDescriptionCall) {
		this.communicationDescriptionCall = communicationDescriptionCall;
	}

	@Override
	public void setEnableType(final EnableType enableType) {
		this.enableType = enableType;
	}

	@Override
	public void setKeyValueStmt(final ValueStmt keyValueStmt) {
		this.keyValueStmt = keyValueStmt;
	}

	@Override
	public void setTerminal(final boolean terminal) {
		this.terminal = terminal;
	}

}
