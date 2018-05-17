/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report;

import io.proleap.cobol.CobolParser.ReportGroupNextGroupClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupTypeClauseContext;

public interface ReportGroupDescriptionEntryVertical extends ReportGroupDescriptionEntry {

	NextGroupClause addNextGroupClause(ReportGroupNextGroupClauseContext ctx);

	TypeClause addTypeClause(ReportGroupTypeClauseContext ctx);

	NextGroupClause getNextGroupClause();

	TypeClause getTypeClause();

}
