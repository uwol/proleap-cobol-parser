/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.disable.impl;

import io.proleap.cobol.CobolParser.DisableStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.disable.DisableStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class DisableStatementImpl extends StatementImpl implements DisableStatement {

	protected Call communicationDescriptionCall;

	protected final DisableStatementContext ctx;

	protected DisableType disableType;

	protected ValueStmt keyValueStmt;

	protected final StatementType statementType = StatementTypeEnum.DISABLE;

	protected boolean terminal;

	public DisableStatementImpl(final ProgramUnit programUnit, final Scope scope, final DisableStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getCommunicationDescriptionCall() {
		return communicationDescriptionCall;
	}

	@Override
	public DisableType getDisableType() {
		return disableType;
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
	public void setDisableType(final DisableType disableType) {
		this.disableType = disableType;
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
