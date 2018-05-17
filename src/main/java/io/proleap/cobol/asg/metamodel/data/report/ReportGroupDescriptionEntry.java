/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report;

import java.util.List;

import io.proleap.cobol.CobolParser.ReportGroupLineNumberClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupUsageClauseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.Declaration;

public interface ReportGroupDescriptionEntry extends CobolDivisionElement, Declaration {

	enum ReportGroupDescriptionEntryType {
		PRINTABLE, SINGLE, VERTICAL
	}

	UsageClause addGroupUsageClause(ReportGroupUsageClauseContext ctx);

	LineNumberClause addLineNumberClause(ReportGroupLineNumberClauseContext ctx);

	void addReportGroupDescriptionEntry(ReportGroupDescriptionEntry reportGroupDescriptionEntry);

	Integer getLevelNumber();

	LineNumberClause getLineNumberClause();

	ReportGroupDescriptionEntry getParentReportGroupDescriptionEntry();

	List<ReportGroupDescriptionEntry> getReportGroupDescriptionEntries();

	ReportGroupDescriptionEntry getReportGroupDescriptionEntry(String name);

	ReportGroupDescriptionEntryType getReportGroupDescriptionEntryType();

	UsageClause getUsageClause();

	void setLevelNumber(Integer levelNumber);

	void setParentReportGroupDescriptionEntry(ReportGroupDescriptionEntry parentReportGroupDescriptionEntry);

}
