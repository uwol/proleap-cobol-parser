/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.ReportDescriptionFirstDetailClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionFootingClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionGlobalClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionHeadingClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionLastDetailClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionPageLimitClauseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.Declaration;
import io.proleap.cobol.asg.metamodel.call.ReportDescriptionEntryCall;

public interface ReportDescriptionEntry extends CobolDivisionElement, Declaration {

	void addCall(ReportDescriptionEntryCall call);

	FirstDetailClause addFirstDetailClause(ReportDescriptionFirstDetailClauseContext ctx);

	FootingClause addFootingClause(ReportDescriptionFootingClauseContext ctx);

	GlobalClause addGlobalClause(ReportDescriptionGlobalClauseContext ctx);

	HeadingClause addHeadingClause(ReportDescriptionHeadingClauseContext ctx);

	LastDetailClause addLastDetailClause(ReportDescriptionLastDetailClauseContext ctx);

	PageLimitClause addPageLimitClause(ReportDescriptionPageLimitClauseContext ctx);

	List<ReportDescriptionEntryCall> getCalls();

	FirstDetailClause getFirstDetailClause();

	FootingClause getFootingClause();

	GlobalClause getGlobalClause();

	HeadingClause getHeadingClause();

	LastDetailClause getLastDetailClause();

	PageLimitClause getPageLimitClause();

	Report getReport();

	void setReport(Report report);
}
