/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntriesSymbolTableEntry;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntry;

public class CommunicationDescriptionEntriesSymbolTableEntryImpl
		implements CommunicationDescriptionEntriesSymbolTableEntry {

	private final static Logger LOG = LoggerFactory
			.getLogger(CommunicationDescriptionEntriesSymbolTableEntryImpl.class);

	protected List<CommunicationDescriptionEntry> communicationDescriptionEntries = new ArrayList<CommunicationDescriptionEntry>();

	@Override
	public void addCommunicationDescriptionEntry(final CommunicationDescriptionEntry communicationDescriptionEntry) {
		if (!communicationDescriptionEntries.isEmpty()) {
			LOG.debug("multiple declarations of communication description entry {}", communicationDescriptionEntry);
		}

		communicationDescriptionEntries.add(communicationDescriptionEntry);
	}

	@Override
	public List<CommunicationDescriptionEntry> getCommunicationDescriptionEntries() {
		return communicationDescriptionEntries;
	}

	@Override
	public CommunicationDescriptionEntry getCommunicationDescriptionEntry() {
		return communicationDescriptionEntries.isEmpty() ? null : communicationDescriptionEntries.get(0);
	}
}
