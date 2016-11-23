/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.proleap.cobol.Cobol85Parser.ReportContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.ReportSectionContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.Report;
import io.proleap.cobol.parser.metamodel.data.report.ReportGroupDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.report.ReportSection;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class ReportSectionImpl extends CobolDivisionElementImpl implements ReportSection {

	protected final ReportSectionContext ctx;

	protected List<Report> reports = new ArrayList<Report>();

	protected Map<String, Report> reportsSymbolTable = new HashMap<String, Report>();

	public ReportSectionImpl(final ProgramUnit programUnit, final ReportSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Report addReport(final ReportContext ctx) {
		Report result = (Report) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ReportImpl(name, programUnit, ctx);

			/*
			 * report description
			 */
			final ReportDescriptionEntryContext reportDescriptionEntryContext = ctx.reportDescriptionEntry();
			result.addReportDescriptionEntry(reportDescriptionEntryContext);

			/*
			 * report group descriptions
			 */
			ReportGroupDescriptionEntry lastReportGroupDescriptionEntry = null;

			for (final ReportGroupDescriptionEntryContext reportGroupDescriptionEntryContext : ctx
					.reportGroupDescriptionEntry()) {
				final ReportGroupDescriptionEntry reportGroupDescriptionEntry = result
						.createReportGroupDescriptionEntry(lastReportGroupDescriptionEntry,
								reportGroupDescriptionEntryContext);
				lastReportGroupDescriptionEntry = reportGroupDescriptionEntry;
			}

			reports.add(result);
			reportsSymbolTable.put(name, result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Report getReport(final String name) {
		return reportsSymbolTable.get(name);
	}

	@Override
	public List<Report> getReports() {
		return reports;
	}

}
