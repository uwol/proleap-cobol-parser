/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.disable.impl;

import io.proleap.cobol.Cobol85Parser.DisableStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.disable.DisableStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class DisableStatementImpl extends StatementImpl implements DisableStatement {

	protected Call communicationDescriptionCall;

	protected final DisableStatementContext ctx;

	protected Call keyCall;

	protected final StatementType statementType = StatementTypeEnum.DISABLE;

	protected boolean terminal;

	protected Type type;

	public DisableStatementImpl(final ProgramUnit programUnit, final Scope scope, final DisableStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getCommunicationDescriptionCall() {
		return communicationDescriptionCall;
	}

	@Override
	public Call getKeyCall() {
		return keyCall;
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
	public boolean isTerminal() {
		return terminal;
	}

	@Override
	public void setCommunicationDescriptionCall(final Call communicationDescriptionCall) {
		this.communicationDescriptionCall = communicationDescriptionCall;
	}

	@Override
	public void setKeyCall(final Call keyCall) {
		this.keyCall = keyCall;
	}

	@Override
	public void setTerminal(final boolean terminal) {
		this.terminal = terminal;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
