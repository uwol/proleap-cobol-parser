/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.CobolParser.CommunicationDescriptionEntryContext;
import io.proleap.cobol.CobolParser.CommunicationDescriptionEntryFormat1Context;
import io.proleap.cobol.CobolParser.CommunicationDescriptionEntryFormat2Context;
import io.proleap.cobol.CobolParser.CommunicationDescriptionEntryFormat3Context;
import io.proleap.cobol.CobolParser.CommunicationSectionContext;
import io.proleap.cobol.CobolParser.DestinationCountClauseContext;
import io.proleap.cobol.CobolParser.DestinationTableClauseContext;
import io.proleap.cobol.CobolParser.EndKeyClauseContext;
import io.proleap.cobol.CobolParser.ErrorKeyClauseContext;
import io.proleap.cobol.CobolParser.MessageCountClauseContext;
import io.proleap.cobol.CobolParser.MessageDateClauseContext;
import io.proleap.cobol.CobolParser.MessageTimeClauseContext;
import io.proleap.cobol.CobolParser.StatusKeyClauseContext;
import io.proleap.cobol.CobolParser.SymbolicDestinationClauseContext;
import io.proleap.cobol.CobolParser.SymbolicQueueClauseContext;
import io.proleap.cobol.CobolParser.SymbolicSourceClauseContext;
import io.proleap.cobol.CobolParser.SymbolicSubQueueClauseContext;
import io.proleap.cobol.CobolParser.SymbolicTerminalClauseContext;
import io.proleap.cobol.CobolParser.TextLengthClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntriesSymbolTableEntry;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntryInput;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntryInputOutput;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntryOutput;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.asg.metamodel.data.datadescription.impl.DataDescriptionEntryContainerImpl;

public class CommunicationSectionImpl extends DataDescriptionEntryContainerImpl implements CommunicationSection {

	private final static Logger LOG = LoggerFactory.getLogger(CommunicationSectionImpl.class);

	protected List<CommunicationDescriptionEntry> communicationDescriptionEntries = new ArrayList<CommunicationDescriptionEntry>();

	protected Map<String, CommunicationDescriptionEntriesSymbolTableEntry> communicationDescriptionEntriesSymbolTable = new HashMap<String, CommunicationDescriptionEntriesSymbolTableEntry>();

	protected final DataDescriptionEntryContainerType containerType = DataDescriptionEntryContainerType.COMMUNICATION_SECTION;

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
			assureCommunicationDescriptionEntriesSymbolTableEntry(name).addCommunicationDescriptionEntry(result);
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
			assureCommunicationDescriptionEntriesSymbolTableEntry(name).addCommunicationDescriptionEntry(result);
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
			assureCommunicationDescriptionEntriesSymbolTableEntry(name).addCommunicationDescriptionEntry(result);
			registerASGElement(result);
		}

		return result;
	}

	protected CommunicationDescriptionEntriesSymbolTableEntry assureCommunicationDescriptionEntriesSymbolTableEntry(
			final String name) {
		CommunicationDescriptionEntriesSymbolTableEntry communicationDescriptionEntriesSymbolTableEntry = communicationDescriptionEntriesSymbolTable
				.get(getSymbol(name));

		if (communicationDescriptionEntriesSymbolTableEntry == null) {
			communicationDescriptionEntriesSymbolTableEntry = new CommunicationDescriptionEntriesSymbolTableEntryImpl();
			communicationDescriptionEntriesSymbolTable.put(getSymbol(name),
					communicationDescriptionEntriesSymbolTableEntry);
		}

		return communicationDescriptionEntriesSymbolTableEntry;
	}

	@Override
	public CommunicationDescriptionEntry createCommunicationDescriptionEntry(
			final CommunicationDescriptionEntryContext ctx) {
		final CommunicationDescriptionEntry result;

		if (ctx.communicationDescriptionEntryFormat1() != null) {
			result = addCommunicationDescriptionEntryInput(ctx.communicationDescriptionEntryFormat1());
		} else if (ctx.communicationDescriptionEntryFormat2() != null) {
			result = addCommunicationDescriptionEntryOutput(ctx.communicationDescriptionEntryFormat2());
		} else if (ctx.communicationDescriptionEntryFormat3() != null) {
			result = addCommunicationDescriptionEntryInputOutput(ctx.communicationDescriptionEntryFormat3());
		} else {
			LOG.warn("unknown communication description entry {}", ctx);
			result = null;
		}

		return result;
	}

	@Override
	public List<CommunicationDescriptionEntry> getCommunicationDescriptionEntries() {
		return communicationDescriptionEntries;
	}

	@Override
	public List<CommunicationDescriptionEntry> getCommunicationDescriptionEntries(final String name) {
		return communicationDescriptionEntriesSymbolTable.get(getSymbol(name)) == null ? null
				: communicationDescriptionEntriesSymbolTable.get(getSymbol(name)).getCommunicationDescriptionEntries();
	}

	@Override
	public CommunicationDescriptionEntry getCommunicationDescriptionEntry(final String name) {
		return communicationDescriptionEntriesSymbolTable.get(getSymbol(name)) == null ? null
				: communicationDescriptionEntriesSymbolTable.get(getSymbol(name)).getCommunicationDescriptionEntry();
	}

	@Override
	public DataDescriptionEntryContainerType getContainerType() {
		return containerType;
	}
}
