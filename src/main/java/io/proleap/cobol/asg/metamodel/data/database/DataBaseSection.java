/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.database;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.DataBaseSectionEntryContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface DataBaseSection extends CobolDivisionElement {

	DataBaseSectionEntry addDataBaseSectionEntry(DataBaseSectionEntryContext ctx);

	List<DataBaseSectionEntry> getDataBaseSectionEntries();

}
