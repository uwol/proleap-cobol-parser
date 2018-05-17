/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report;

import java.util.List;

import io.proleap.cobol.CobolParser.ReportDescriptionFirstDetailClauseContext;
import io.proleap.cobol.CobolParser.ReportDescriptionFootingClauseContext;
import io.proleap.cobol.CobolParser.ReportDescriptionGlobalClauseContext;
import io.proleap.cobol.CobolParser.ReportDescriptionHeadingClauseContext;
import io.proleap.cobol.CobolParser.ReportDescriptionLastDetailClauseContext;
import io.proleap.cobol.CobolParser.ReportDescriptionPageLimitClauseContext;
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

	ReportDescription getReport();

	void setReport(ReportDescription report);
}
