/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat3Context;
import io.proleap.cobol.Cobol85Parser.CommunicationSectionContext;
import io.proleap.cobol.Cobol85Parser.DestinationCountClauseContext;
import io.proleap.cobol.Cobol85Parser.DestinationTableClauseContext;
import io.proleap.cobol.Cobol85Parser.EndKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.ErrorKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.MessageCountClauseContext;
import io.proleap.cobol.Cobol85Parser.MessageDateClauseContext;
import io.proleap.cobol.Cobol85Parser.MessageTimeClauseContext;
import io.proleap.cobol.Cobol85Parser.StatusKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicDestinationClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicQueueClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicSourceClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicSubQueueClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicTerminalClauseContext;
import io.proleap.cobol.Cobol85Parser.TextLengthClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntryInput;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntryInputOutput;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntryOutput;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.parser.metamodel.data.datadescription.impl.DataDescriptionEntryContainerImpl;

public class CommunicationSectionImpl extends DataDescriptionEntryContainerImpl implements CommunicationSection {

	private final static Logger LOG = LogManager.getLogger(CommunicationSectionImpl.class);

	protected List<CommunicationDescriptionEntry> communicationDescriptionEntries = new ArrayList<CommunicationDescriptionEntry>();

	protected Map<String, CommunicationDescriptionEntry> communicationDescriptionEntriesByName = new HashMap<String, CommunicationDescriptionEntry>();

	protected final CommunicationSectionContext ctx;

	public CommunicationSectionImpl(final ProgramUnit programUnit, final CommunicationSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public CommunicationDescriptionEntryInput addCommunicationDescriptionEntryInput(
			final CommunicationDescriptionEntryFormat1Context ctx) {
		CommunicationDescriptionEntryInput result = (CommunicationDescriptionEntryInput) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx.cdName());
			result = new CommunicationDescriptionEntryInputImpl(name, programUnit, ctx);

			/*
			 * symbolic queue
			 */
			final List<SymbolicQueueClauseContext> symbolicQueueClauseContexts = ctx.symbolicQueueClause();

			if (!symbolicQueueClauseContexts.isEmpty()) {
				final SymbolicQueueClauseContext symbolicQueueClauseContext = symbolicQueueClauseContexts.get(0);
				result.addSymbolicQueueClause(symbolicQueueClauseContext);
			}

			/*
			 * symbolic sub queue
			 */
			final List<SymbolicSubQueueClauseContext> symbolicSubQueueClauseContexts = ctx.symbolicSubQueueClause();

			if (!symbolicSubQueueClauseContexts.isEmpty()) {
				final SymbolicSubQueueClauseContext symbolicSubQueueClauseContext = symbolicSubQueueClauseContexts
						.get(0);
				result.addSymbolicSubQueueClause(symbolicSubQueueClauseContext);
			}

			/*
			 * message date
			 */
			final List<MessageDateClauseContext> messageDateClauseContexts = ctx.messageDateClause();

			if (!messageDateClauseContexts.isEmpty()) {
				final MessageDateClauseContext messageDateClauseContext = messageDateClauseContexts.get(0);
				result.addMessageDateClause(messageDateClauseContext);
			}

			/*
			 * message time
			 */
			final List<MessageTimeClauseContext> messageTimeClauseContexts = ctx.messageTimeClause();

			if (!messageTimeClauseContexts.isEmpty()) {
				final MessageTimeClauseContext messageTimeClauseContext = messageTimeClauseContexts.get(0);
				result.addMessageTimeClause(messageTimeClauseContext);
			}

			/*
			 * symbolic source
			 */
			final List<SymbolicSourceClauseContext> symbolicSourceClauseContexts = ctx.symbolicSourceClause();

			if (!symbolicSourceClauseContexts.isEmpty()) {
				final SymbolicSourceClauseContext symbolicSourceClauseContext = symbolicSourceClauseContexts.get(0);
				result.addSymbolicSourceClause(symbolicSourceClauseContext);
			}

			/*
			 * text length
			 */
			final List<TextLengthClauseContext> textLengthClauseContexts = ctx.textLengthClause();

			if (!textLengthClauseContexts.isEmpty()) {
				final TextLengthClauseContext textLengthClauseContext = textLengthClauseContexts.get(0);
				result.addTextLengthClause(textLengthClauseContext);
			}

			/*
			 * end key
			 */
			final List<EndKeyClauseContext> endKeyClauseContexts = ctx.endKeyClause();

			if (!endKeyClauseContexts.isEmpty()) {
				final EndKeyClauseContext endKeyClauseContext = endKeyClauseContexts.get(0);
				result.addEndKeyClause(endKeyClauseContext);
			}

			/*
			 * status key
			 */
			final List<StatusKeyClauseContext> statusKeyClauseContexts = ctx.statusKeyClause();

			if (!statusKeyClauseContexts.isEmpty()) {
				final StatusKeyClauseContext statusKeyClauseContext = statusKeyClauseContexts.get(0);
				result.addStatusKeyClause(statusKeyClauseContext);
			}

			/*
			 * message count
			 */
			final List<MessageCountClauseContext> messageCountClauseContexts = ctx.messageCountClause();

			if (!messageCountClauseContexts.isEmpty()) {
				final MessageCountClauseContext messageCountClauseContext = messageCountClauseContexts.get(0);
				result.addMessageCountClause(messageCountClauseContext);
			}

			communicationDescriptionEntries.add(result);
			communicationDescriptionEntriesByName.put(name, result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CommunicationDescriptionEntryInputOutput addCommunicationDescriptionEntryInputOutput(
			final CommunicationDescriptionEntryFormat3Context ctx) {
		CommunicationDescriptionEntryInputOutput result = (CommunicationDescriptionEntryInputOutput) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx.cdName());
			result = new CommunicationDescriptionEntryInputOutputImpl(name, programUnit, ctx);

			/*
			 * message date
			 */
			final List<MessageDateClauseContext> messageDateClauseContexts = ctx.messageDateClause();

			if (!messageDateClauseContexts.isEmpty()) {
				final MessageDateClauseContext messageDateClauseContext = messageDateClauseContexts.get(0);
				result.addMessageDateClause(messageDateClauseContext);
			}

			/*
			 * message time
			 */
			final List<MessageTimeClauseContext> messageTimeClauseContexts = ctx.messageTimeClause();

			if (!messageTimeClauseContexts.isEmpty()) {
				final MessageTimeClauseContext messageTimeClauseContext = messageTimeClauseContexts.get(0);
				result.addMessageTimeClause(messageTimeClauseContext);
			}

			/*
			 * symbolic terminal
			 */
			final List<SymbolicTerminalClauseContext> symbolicTerminalClauseContexts = ctx.symbolicTerminalClause();

			if (!symbolicTerminalClauseContexts.isEmpty()) {
				final SymbolicTerminalClauseContext symbolicTerminalClauseContext = symbolicTerminalClauseContexts
						.get(0);
				result.addSymbolicTerminalClause(symbolicTerminalClauseContext);
			}

			/*
			 * text length
			 */
			final List<TextLengthClauseContext> textLengthClauseContexts = ctx.textLengthClause();

			if (!textLengthClauseContexts.isEmpty()) {
				final TextLengthClauseContext textLengthClauseContext = textLengthClauseContexts.get(0);
				result.addTextLengthClause(textLengthClauseContext);
			}

			/*
			 * end key
			 */
			final List<EndKeyClauseContext> endKeyClauseContexts = ctx.endKeyClause();

			if (!endKeyClauseContexts.isEmpty()) {
				final EndKeyClauseContext endKeyClauseContext = endKeyClauseContexts.get(0);
				result.addEndKeyClause(endKeyClauseContext);
			}

			/*
			 * status key
			 */
			final List<StatusKeyClauseContext> statusKeyClauseContexts = ctx.statusKeyClause();

			if (!statusKeyClauseContexts.isEmpty()) {
				final StatusKeyClauseContext statusKeyClauseContext = statusKeyClauseContexts.get(0);
				result.addStatusKeyClause(statusKeyClauseContext);
			}

			communicationDescriptionEntries.add(result);
			communicationDescriptionEntriesByName.put(name, result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CommunicationDescriptionEntryOutput addCommunicationDescriptionEntryOutput(
			final CommunicationDescriptionEntryFormat2Context ctx) {
		CommunicationDescriptionEntryOutput result = (CommunicationDescriptionEntryOutput) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx.cdName());
			result = new CommunicationDescriptionEntryOutputImpl(name, programUnit, ctx);

			/*
			 * destination count
			 */
			final List<DestinationCountClauseContext> destinationCountClauseContexts = ctx.destinationCountClause();

			if (!destinationCountClauseContexts.isEmpty()) {
				final DestinationCountClauseContext destinationCountClauseContext = destinationCountClauseContexts
						.get(0);
				result.addDestinationCountClause(destinationCountClauseContext);
			}

			/*
			 * text length
			 */
			final List<TextLengthClauseContext> textLengthClauseContexts = ctx.textLengthClause();

			if (!textLengthClauseContexts.isEmpty()) {
				final TextLengthClauseContext textLengthClauseContext = textLengthClauseContexts.get(0);
				result.addTextLengthClause(textLengthClauseContext);
			}

			/*
			 * status key
			 */
			final List<StatusKeyClauseContext> statusKeyClauseContexts = ctx.statusKeyClause();

			if (!statusKeyClauseContexts.isEmpty()) {
				final StatusKeyClauseContext statusKeyClauseContext = statusKeyClauseContexts.get(0);
				result.addStatusKeyClause(statusKeyClauseContext);
			}

			/*
			 * destination table
			 */
			final List<DestinationTableClauseContext> destinationTableClauseContexts = ctx.destinationTableClause();

			if (!destinationTableClauseContexts.isEmpty()) {
				final DestinationTableClauseContext destinationTableClauseContext = destinationTableClauseContexts
						.get(0);
				result.addDestinationTableClause(destinationTableClauseContext);
			}

			/*
			 * error key
			 */
			final List<ErrorKeyClauseContext> errorKeyClauseContexts = ctx.errorKeyClause();

			if (!errorKeyClauseContexts.isEmpty()) {
				final ErrorKeyClauseContext errorKeyClauseContext = errorKeyClauseContexts.get(0);
				result.addErrorKeyClause(errorKeyClauseContext);
			}

			/*
			 * symbolic destination
			 */
			final List<SymbolicDestinationClauseContext> symbolicDestinationClauseContexts = ctx
					.symbolicDestinationClause();

			if (!symbolicDestinationClauseContexts.isEmpty()) {
				final SymbolicDestinationClauseContext symbolicDestinationClauseContext = symbolicDestinationClauseContexts
						.get(0);
				result.addSymbolicDestinationClause(symbolicDestinationClauseContext);
			}

			communicationDescriptionEntries.add(result);
			communicationDescriptionEntriesByName.put(name, result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CommunicationDescriptionEntry createCommunicationDescriptionEntry(
			final CommunicationDescriptionEntryContext communicationDescriptionEntryContext) {
		final CommunicationDescriptionEntry result;

		if (communicationDescriptionEntryContext.communicationDescriptionEntryFormat1() != null) {
			result = addCommunicationDescriptionEntryInput(
					communicationDescriptionEntryContext.communicationDescriptionEntryFormat1());
		} else if (communicationDescriptionEntryContext.communicationDescriptionEntryFormat2() != null) {
			result = addCommunicationDescriptionEntryOutput(
					communicationDescriptionEntryContext.communicationDescriptionEntryFormat2());
		} else if (communicationDescriptionEntryContext.communicationDescriptionEntryFormat3() != null) {
			result = addCommunicationDescriptionEntryInputOutput(
					communicationDescriptionEntryContext.communicationDescriptionEntryFormat3());
		} else {
			LOG.warn("unknown communication description entry {}", communicationDescriptionEntryContext);
			result = null;
		}

		return result;
	}

	@Override
	public List<CommunicationDescriptionEntry> getCommunicationDescriptionEntries() {
		return communicationDescriptionEntries;
	}

	@Override
	public CommunicationDescriptionEntry getCommunicationDescriptionEntry(final String name) {
		return communicationDescriptionEntriesByName.get(name);
	}
}
