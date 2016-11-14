/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat3Context;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;

public interface DataDescriptionEntryContainer extends CobolDivisionElement {

	DataDescriptionEntryCondition addDataDescriptionEntryCondition(DataDescriptionEntryFormat3Context ctx);

	DataDescriptionEntryGroup addDataDescriptionEntryGroup(DataDescriptionEntryFormat1Context ctx);

	DataDescriptionEntryRename addDataDescriptionEntryRename(DataDescriptionEntryFormat2Context ctx);

	DataDescriptionEntry createDataDescriptionEntry(DataDescriptionEntryGroup lastDataDescriptionEntryGroup,
			DataDescriptionEntryContext dataDescriptionEntryContext);

	List<DataDescriptionEntry> getDataDescriptionEntries();

	DataDescriptionEntry getDataDescriptionEntry(String name);

	List<DataDescriptionEntry> getRootDataDescriptionEntries();
}
