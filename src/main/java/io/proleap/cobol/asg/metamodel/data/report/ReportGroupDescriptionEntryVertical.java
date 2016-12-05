/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report;

import io.proleap.cobol.Cobol85Parser.ReportGroupNextGroupClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupTypeClauseContext;

public interface ReportGroupDescriptionEntryVertical extends ReportGroupDescriptionEntry {

	NextGroupClause addNextGroupClause(ReportGroupNextGroupClauseContext ctx);

	TypeClause addTypeClause(ReportGroupTypeClauseContext ctx);

	NextGroupClause getNextGroupClause();

	TypeClause getTypeClause();

}
