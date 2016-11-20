/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.ReportDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.ReportGroupDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.ReportGroupDescriptionEntryFormat3Context;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.Declaration;

public interface Report extends CobolDivisionElement, Declaration {

	ReportDescriptionEntry addReportDescriptionEntry(ReportDescriptionEntryContext ctx);

	ReportGroupDescriptionEntryPrintable addReportGroupDescriptionEntryPrintable(
			ReportGroupDescriptionEntryFormat3Context ctx);

	ReportGroupDescriptionEntrySingle addReportGroupDescriptionEntrySingle(
			ReportGroupDescriptionEntryFormat2Context ctx);

	ReportGroupDescriptionEntryVertical addReportGroupDescriptionEntryVertical(
			ReportGroupDescriptionEntryFormat1Context ctx);

	ReportGroupDescriptionEntry createReportGroupDescriptionEntry(
			ReportGroupDescriptionEntry lastReportGroupDescriptionEntry, ReportGroupDescriptionEntryContext ctx);

	ReportDescriptionEntry getReportDescriptionEntry();

	List<ReportGroupDescriptionEntry> getReportGroupDescriptionEntries();

	ReportGroupDescriptionEntry getReportGroupDescriptionEntry(String name);

	List<ReportGroupDescriptionEntry> getRootReportGroupDescriptionEntries();

}
