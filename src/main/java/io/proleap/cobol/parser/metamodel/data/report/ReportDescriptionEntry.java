/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report;

import io.proleap.cobol.Cobol85Parser.ReportDescriptionFirstDetailClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionFootingClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionGlobalClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionHeadingClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionLastDetailClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionPageLimitClauseContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.Declaration;

public interface ReportDescriptionEntry extends CobolDivisionElement, Declaration {

	FirstDetailClause addFirstDetailClause(ReportDescriptionFirstDetailClauseContext ctx);

	FootingClause addFootingClause(ReportDescriptionFootingClauseContext ctx);

	GlobalClause addGlobalClause(ReportDescriptionGlobalClauseContext ctx);

	HeadingClause addHeadingClause(ReportDescriptionHeadingClauseContext ctx);

	LastDetailClause addLastDetailClause(ReportDescriptionLastDetailClauseContext ctx);

	PageLimitClause addPageLimitClause(ReportDescriptionPageLimitClauseContext ctx);

	FirstDetailClause getFirstDetailClause();

	FootingClause getFootingClause();

	GlobalClause getGlobalClause();

	HeadingClause getHeadingClause();

	LastDetailClause getLastDetailClause();

	PageLimitClause getPageLimitClause();
}
