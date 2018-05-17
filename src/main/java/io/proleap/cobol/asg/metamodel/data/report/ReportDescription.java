/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report;

import java.util.List;

import io.proleap.cobol.CobolParser.ReportDescriptionEntryContext;
import io.proleap.cobol.CobolParser.ReportGroupDescriptionEntryContext;
import io.proleap.cobol.CobolParser.ReportGroupDescriptionEntryFormat1Context;
import io.proleap.cobol.CobolParser.ReportGroupDescriptionEntryFormat2Context;
import io.proleap.cobol.CobolParser.ReportGroupDescriptionEntryFormat3Context;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.Declaration;
import io.proleap.cobol.asg.metamodel.call.ReportCall;

public interface ReportDescription extends CobolDivisionElement, Declaration {

	void addCall(ReportCall call);

	ReportDescriptionEntry addReportDescriptionEntry(ReportDescriptionEntryContext ctx);

	ReportGroupDescriptionEntryPrintable addReportGroupDescriptionEntryPrintable(
			ReportGroupDescriptionEntryFormat3Context ctx);

	ReportGroupDescriptionEntrySingle addReportGroupDescriptionEntrySingle(
			ReportGroupDescriptionEntryFormat2Context ctx);

	ReportGroupDescriptionEntryVertical addReportGroupDescriptionEntryVertical(
			ReportGroupDescriptionEntryFormat1Context ctx);

	ReportGroupDescriptionEntry createReportGroupDescriptionEntry(
			ReportGroupDescriptionEntry lastReportGroupDescriptionEntry, ReportGroupDescriptionEntryContext ctx);

	List<ReportCall> getCalls();

	ReportDescriptionEntry getReportDescriptionEntry();

	List<ReportGroupDescriptionEntry> getReportGroupDescriptionEntries();

	List<ReportGroupDescriptionEntry> getReportGroupDescriptionEntries(String name);

	ReportGroupDescriptionEntry getReportGroupDescriptionEntry(String name);

	List<ReportGroupDescriptionEntry> getRootReportGroupDescriptionEntries();

}
