/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription;

import io.proleap.cobol.CobolParser.DataRenamesClauseContext;

public interface DataDescriptionEntryRename extends DataDescriptionEntry {

	RenamesClause addRenamesClause(DataRenamesClauseContext ctx);

	RenamesClause getRenamesClause();
}
