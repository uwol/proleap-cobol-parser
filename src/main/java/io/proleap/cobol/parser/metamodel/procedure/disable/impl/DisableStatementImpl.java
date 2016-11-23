/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.disable.impl;

import io.proleap.cobol.Cobol85Parser.DisableStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.disable.DisableStatement;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class DisableStatementImpl extends StatementImpl implements DisableStatement {

	protected Call communicationDescriptionCall;

	protected final DisableStatementContext ctx;

	protected Call keyCall;

	protected boolean terminal;

	protected Type type;

	public DisableStatementImpl(final ProgramUnit programUnit, final DisableStatementContext ctx) {
		super(programUnit, ctx);

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
