/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication.impl;

import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat3Context;
import io.proleap.cobol.Cobol85Parser.EndKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.MessageDateClauseContext;
import io.proleap.cobol.Cobol85Parser.MessageTimeClauseContext;
import io.proleap.cobol.Cobol85Parser.StatusKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicTerminalClauseContext;
import io.proleap.cobol.Cobol85Parser.TextLengthClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntryInputOutput;
import io.proleap.cobol.asg.metamodel.data.communication.EndKeyClause;
import io.proleap.cobol.asg.metamodel.data.communication.MessageDateClause;
import io.proleap.cobol.asg.metamodel.data.communication.MessageTimeClause;
import io.proleap.cobol.asg.metamodel.data.communication.StatusKeyClause;
import io.proleap.cobol.asg.metamodel.data.communication.SymbolicTerminalClause;
import io.proleap.cobol.asg.metamodel.data.communication.TextLengthClause;

public class CommunicationDescriptionEntryInputOutputImpl extends CommunicationDescriptionEntryImpl
		implements CommunicationDescriptionEntryInputOutput {

	protected final CommunicationDescriptionEntryFormat3Context ctx;

	protected EndKeyClause endKeyClause;

	protected MessageDateClause messageDateClause;

	protected MessageTimeClause messageTimeClause;

	protected StatusKeyClause statusKeyClause;

	protected SymbolicTerminalClause symbolicTerminalClause;

	protected TextLengthClause textLengthClause;

	public CommunicationDescriptionEntryInputOutputImpl(final String name, final ProgramUnit programUnit,
			final CommunicationDescriptionEntryFormat3Context ctx) {
		super(name, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public EndKeyClause addEndKeyClause(final EndKeyClauseContext ctx) {
		EndKeyClause result = (EndKeyClause) getASGElement(ctx);

		if (result == null) {
			result = new EndKeyClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			endKeyClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MessageDateClause addMessageDateClause(final MessageDateClauseContext ctx) {
		MessageDateClause result = (MessageDateClause) getASGElement(ctx);

		if (result == null) {
			result = new MessageDateClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			messageDateClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MessageTimeClause addMessageTimeClause(final MessageTimeClauseContext ctx) {
		MessageTimeClause result = (MessageTimeClause) getASGElement(ctx);

		if (result == null) {
			result = new MessageTimeClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			messageTimeClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public StatusKeyClause addStatusKeyClause(final StatusKeyClauseContext ctx) {
		StatusKeyClause result = (StatusKeyClause) getASGElement(ctx);

		if (result == null) {
			result = new StatusKeyClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			statusKeyClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SymbolicTerminalClause addSymbolicTerminalClause(final SymbolicTerminalClauseContext ctx) {
		SymbolicTerminalClause result = (SymbolicTerminalClause) getASGElement(ctx);

		if (result == null) {
			result = new SymbolicTerminalClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			symbolicTerminalClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public TextLengthClause addTextLengthClause(final TextLengthClauseContext ctx) {
		TextLengthClause result = (TextLengthClause) getASGElement(ctx);

		if (result == null) {
			result = new TextLengthClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			textLengthClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public EndKeyClause getEndKeyClause() {
		return endKeyClause;
	}

	@Override
	public MessageDateClause getMessageDateClause() {
		return messageDateClause;
	}

	@Override
	public MessageTimeClause getMessageTimeClause() {
		return messageTimeClause;
	}

	@Override
	public StatusKeyClause getStatusKeyClause() {
		return statusKeyClause;
	}

	@Override
	public SymbolicTerminalClause getSymbolicTerminalClause() {
		return symbolicTerminalClause;
	}

	@Override
	public TextLengthClause getTextLengthClause() {
		return textLengthClause;
	}

	@Override
	public Type getType() {
		return Type.InputOutput;
	}

}
