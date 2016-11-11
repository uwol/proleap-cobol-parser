/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat3Context;
import io.proleap.cobol.Cobol85Parser.DataDivisionBodyContext;
import io.proleap.cobol.Cobol85Parser.FileDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.FileSectionContext;
import io.proleap.cobol.Cobol85Parser.WorkingStorageSectionContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;

public interface DataDivision extends CobolDivision {

	static final int LEVEL_NUMBER_RENAME = 66;

	static final int LEVEL_NUMBER_SCALAR = 77;

	static final int LINE_NUMBER_CONDITION = 88;

	DataDescriptionEntryCondition addDataDescriptionEntryCondition(DataDescriptionEntryFormat3Context ctx);

	DataDescriptionEntryGroup addDataDescriptionEntryGroup(DataDescriptionEntryFormat1Context ctx);

	DataDescriptionEntryRename addDataDescriptionEntryRename(DataDescriptionEntryFormat2Context ctx);

	DataDivisionBody addDataDivisionBody(DataDivisionBodyContext ctx);

	FileDescriptionEntry addFileDescriptionEntry(FileDescriptionEntryContext ctx);

	FileSection addFileSection(FileSectionContext ctx);

	WorkingStorageSection addWorkingStorageSection(WorkingStorageSectionContext ctx);

	List<DataDescriptionEntry> getDataDescriptionEntries();

	DataDescriptionEntry getDataDescriptionEntry(String name);

	DataDescriptionEntryCondition getDataDescriptionEntryCondition(String name);

	List<DataDescriptionEntryCondition> getDataDescriptionEntryConditions();

	DataDescriptionEntryGroup getDataDescriptionEntryGroup(String name);

	List<DataDescriptionEntryGroup> getDataDescriptionEntryGroups();

	DataDescriptionEntryRename getDataDescriptionEntryRename(String name);

	List<DataDescriptionEntryRename> getDataDescriptionEntryRenames();

	DataDivisionBody getDataDivisionBody();

	List<FileDescriptionEntry> getFileDescriptionEntries();

	FileDescriptionEntry getFileDescriptionEntry(String name);

	void setDataDivisionBody(DataDivisionBody dataDivisionBody);

}
