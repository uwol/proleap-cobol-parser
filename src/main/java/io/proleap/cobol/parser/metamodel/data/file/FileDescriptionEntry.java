/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file;

import io.proleap.cobol.Cobol85Parser.BlockContainsClauseContext;
import io.proleap.cobol.Cobol85Parser.LabelRecordsClauseContext;
import io.proleap.cobol.Cobol85Parser.RecordContainsClauseContext;
import io.proleap.cobol.parser.metamodel.Declaration;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntryContainer;

public interface FileDescriptionEntry extends DataDescriptionEntryContainer, Declaration {

	BlockContainsClause addBlockContainsClause(BlockContainsClauseContext ctx);

	LabelRecordsClause addLabelRecordsClause(LabelRecordsClauseContext ctx);

	RecordContainsClause addRecordContainsClause(RecordContainsClauseContext ctx);

	BlockContainsClause getBlockContainsClause();

	LabelRecordsClause getLabelRecordsClause();

	RecordContainsClause getRecordContainsClause();

	Boolean isExternal();

	Boolean isGlobal();

	void setExternal(Boolean external);

	void setGlobal(Boolean global);

}
