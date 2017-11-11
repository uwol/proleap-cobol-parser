/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.asg.metamodel.data.screen.ScreenDescriptionEntriesSymbolTableEntry;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenDescriptionEntry;

public class ScreenDescriptionEntriesSymbolTableEntryImpl implements ScreenDescriptionEntriesSymbolTableEntry {

	protected final static Logger LOG = LogManager.getLogger(ScreenDescriptionEntriesSymbolTableEntryImpl.class);

	protected List<ScreenDescriptionEntry> screenDescriptionEntries = new ArrayList<ScreenDescriptionEntry>();

	@Override
	public void addScreenDescriptionEntry(final ScreenDescriptionEntry screenDescriptionEntry) {
		if (!screenDescriptionEntries.isEmpty()) {
			LOG.info("multiple declarations of screen description entry {}", screenDescriptionEntry);
		}

		screenDescriptionEntries.add(screenDescriptionEntry);
	}

	@Override
	public List<ScreenDescriptionEntry> getScreenDescriptionEntries() {
		return screenDescriptionEntries;
	}

	@Override
	public ScreenDescriptionEntry getScreenDescriptionEntry() {
		return screenDescriptionEntries.isEmpty() ? null : screenDescriptionEntries.get(0);
	}
}
