/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionEntryContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface ScreenSection extends CobolDivisionElement {

	ScreenDescriptionEntry addScreenDescriptionEntry(ScreenDescriptionEntryContext ctx);

	List<ScreenDescriptionEntry> getRootScreenDescriptionEntries();

	List<ScreenDescriptionEntry> getScreenDescriptionEntries();

	List<ScreenDescriptionEntry> getScreenDescriptionEntries(String name);

	ScreenDescriptionEntry getScreenDescriptionEntry(String name);
}
