/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.EndKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.MessageCountClauseContext;
import io.proleap.cobol.Cobol85Parser.MessageDateClauseContext;
import io.proleap.cobol.Cobol85Parser.MessageTimeClauseContext;
import io.proleap.cobol.Cobol85Parser.StatusKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicQueueClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicSourceClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicSubQueueClauseContext;
import io.proleap.cobol.Cobol85Parser.TextLengthClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntryInput;
import io.proleap.cobol.asg.metamodel.data.communication.EndKeyClause;
import io.proleap.cobol.asg.metamodel.data.communication.MessageCountClause;
import io.proleap.cobol.asg.metamodel.data.communication.MessageDateClause;
import io.proleap.cobol.asg.metamodel.data.communication.MessageTimeClause;
import io.proleap.cobol.asg.metamodel.data.communication.StatusKeyClause;
import io.proleap.cobol.asg.metamodel.data.communication.SymbolicQueueClause;
import io.proleap.cobol.asg.metamodel.data.communication.SymbolicSourceClause;
import io.proleap.cobol.asg.metamodel.data.communication.SymbolicSubQueueClause;
import io.proleap.cobol.asg.metamodel.data.communication.TextLengthClause;

public class CommunicationDescriptionEntryInputImpl extends CommunicationDescriptionEntryImpl
		implements CommunicationDescriptionEntryInput {

	private final static Logger LOG = LogManager.getLogger(CommunicationDescriptionEntryInputImpl.class);

	protected final CommunicationDescriptionEntryFormat1Context ctx;

	protected EndKeyClause endKeyClause;

	protected EndKeyClauseContext endKeyClauseContext;

	protected MessageCountClause messageCountClause;

	protected MessageDateClause messageDateClause;

	protected MessageTimeClause messageTimeClause;

	protected StatusKeyClause statusKeyClause;

	protected SymbolicQueueClause symbolicQueueClause;

	protected SymbolicSourceClause symbolicSourceClause;

	protected SymbolicSubQueueClause symbolicSubQueueClause;

	protected TextLengthClause textLengthClause;

	public CommunicationDescriptionEntryInputImpl(final String name, final ProgramUnit programUnit,
			final CommunicationDescriptionEntryFormat1Context ctx) {
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
	public MessageCountClause addMessageCountClause(final MessageCountClauseContext ctx) {
		MessageCountClause result = (MessageCountClause) getASGElement(ctx);

		if (result == null) {
			result = new MessageCountClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			messageCountClause = result;
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
	public SymbolicQueueClause addSymbolicQueueClause(final SymbolicQueueClauseContext ctx) {
		SymbolicQueueClause result = (SymbolicQueueClause) getASGElement(ctx);

		if (result == null) {
			result = new SymbolicQueueClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			symbolicQueueClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SymbolicSourceClause addSymbolicSourceClause(final SymbolicSourceClauseContext ctx) {
		SymbolicSourceClause result = (SymbolicSourceClause) getASGElement(ctx);

		if (result == null) {
			result = new SymbolicSourceClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			symbolicSourceClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SymbolicSubQueueClause addSymbolicSubQueueClause(final SymbolicSubQueueClauseContext ctx) {
		SymbolicSubQueueClause result = (SymbolicSubQueueClause) getASGElement(ctx);

		if (result == null) {
			result = new SymbolicSubQueueClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			/*
			 * sub queue
			 */
			final SymbolicSubQueueClause.Type type;

			if (ctx.SUB_QUEUE_1() != null) {
				type = SymbolicSubQueueClause.Type.SUB_QUEUE_1;
			} else if (ctx.SUB_QUEUE_2() != null) {
				type = SymbolicSubQueueClause.Type.SUB_QUEUE_2;
			} else if (ctx.SUB_QUEUE_3() != null) {
				type = SymbolicSubQueueClause.Type.SUB_QUEUE_3;
			} else {
				LOG.warn("unknown type at {}", ctx);
				type = null;
			}

			result.setType(type);

			symbolicSubQueueClause = result;
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
	public MessageCountClause getMessageCountClause() {
		return messageCountClause;
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
	public SymbolicQueueClause getSymbolicQueueClause() {
		return symbolicQueueClause;
	}

	@Override
	public SymbolicSourceClause getSymbolicSourceClause() {
		return symbolicSourceClause;
	}

	@Override
	public SymbolicSubQueueClause getSymbolicSubQueueClause() {
		return symbolicSubQueueClause;
	}

	@Override
	public TextLengthClause getTextLengthClause() {
		return textLengthClause;
	}

	@Override
	public Type getType() {
		return Type.INPUT;
	}

}
