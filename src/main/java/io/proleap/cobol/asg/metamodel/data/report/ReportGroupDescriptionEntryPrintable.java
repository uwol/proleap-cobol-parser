/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report;

import io.proleap.cobol.Cobol85Parser.ReportGroupBlankWhenZeroClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupColumnNumberClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupIndicateClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupJustifiedClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupPictureClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupResetClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupSignClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupSourceClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupSumClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupValueClauseContext;

public interface ReportGroupDescriptionEntryPrintable extends ReportGroupDescriptionEntry {

	BlankWhenZeroClause addBlankWhenZeroClause(ReportGroupBlankWhenZeroClauseContext ctx);

	ColumnNumberClause addColumnNumberClause(ReportGroupColumnNumberClauseContext ctx);

	GroupIndicateClause addGroupIndicateClause(ReportGroupIndicateClauseContext ctx);

	JustifiedClause addJustifiedClause(ReportGroupJustifiedClauseContext ctx);

	PictureClause addPictureClause(ReportGroupPictureClauseContext ctx);

	ResetClause addResetClause(ReportGroupResetClauseContext ctx);

	SignClause addSignClause(ReportGroupSignClauseContext ctx);

	SourceClause addSourceClause(ReportGroupSourceClauseContext ctx);

	SumClause addSumClause(ReportGroupSumClauseContext ctx);

	ValueClause addValueClause(ReportGroupValueClauseContext ctx);

	BlankWhenZeroClause getBlankWhenZeroClause();

	ColumnNumberClause getColumnNumberClause();

	GroupIndicateClause getGroupIndicateClause();

	JustifiedClause getJustifiedClause();

	PictureClause getPictureClause();

	ResetClause getResetClause();

	SignClause getSignClause();

	SourceClause getSourceClause();

	SumClause getSumClause();

	ValueClause getValueClause();

}
