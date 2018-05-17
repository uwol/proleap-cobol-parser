/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription;

import java.util.List;

import io.proleap.cobol.CobolParser.DataDescriptionEntryContext;
import io.proleap.cobol.CobolParser.DataDescriptionEntryExecSqlContext;
import io.proleap.cobol.CobolParser.DataDescriptionEntryFormat1Context;
import io.proleap.cobol.CobolParser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.CobolParser.DataDescriptionEntryFormat3Context;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface DataDescriptionEntryContainer extends CobolDivisionElement {

	enum DataDescriptionEntryContainerType {
		COMMUNICATION_SECTION, FILE_DESCRIPTION_ENTRY, LINKAGE_SECTION, LOCAL_STORAGE_SECTION, WORKING_STORAGE_SECTION
	}

	DataDescriptionEntryCondition addDataDescriptionEntryCondition(DataDescriptionEntryFormat3Context ctx);

	DataDescriptionEntryExecSql addDataDescriptionEntryExecSql(DataDescriptionEntryExecSqlContext ctx);

	DataDescriptionEntryGroup addDataDescriptionEntryGroup(DataDescriptionEntryFormat1Context ctx);

	DataDescriptionEntryRename addDataDescriptionEntryRename(DataDescriptionEntryFormat2Context ctx);

	DataDescriptionEntry createDataDescriptionEntry(DataDescriptionEntryGroup lastDataDescriptionEntryGroup,
			DataDescriptionEntryContext ctx);

	DataDescriptionEntryContainerType getContainerType();

	/**
	 * Returns every contained @DataDescriptionEntry including nested ones.
	 */
	List<DataDescriptionEntry> getDataDescriptionEntries();

	List<DataDescriptionEntry> getDataDescriptionEntries(String name);

	/**
	 * Returns a contained @DataDescriptionEntry for the given name, including
	 * nested ones.
	 */
	DataDescriptionEntry getDataDescriptionEntry(String name);

	/**
	 * Returns every root @DataDescriptionEntry excluding nested ones.
	 */
	List<DataDescriptionEntry> getRootDataDescriptionEntries();
}
