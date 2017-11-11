/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen;

import java.util.List;

public interface ScreenDescriptionEntriesSymbolTableEntry {

	void addScreenDescriptionEntry(ScreenDescriptionEntry screenDescriptionEntry);

	List<ScreenDescriptionEntry> getScreenDescriptionEntries();

	ScreenDescriptionEntry getScreenDescriptionEntry();
}
