/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.proleap.cobol.CobolParser.ReportDescriptionContext;
import io.proleap.cobol.CobolParser.ReportDescriptionEntryContext;
import io.proleap.cobol.CobolParser.ReportGroupDescriptionEntryContext;
import io.proleap.cobol.CobolParser.ReportSectionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.report.ReportDescription;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.report.ReportSection;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ReportSectionImpl extends CobolDivisionElementImpl implements ReportSection {

	protected final ReportSectionContext ctx;

	protected List<ReportDescription> reportDescriptions = new ArrayList<ReportDescription>();

	protected Map<String, ReportDescription> reportDescriptionsSymbolTable = new HashMap<String, ReportDescription>();

	public ReportSectionImpl(final ProgramUnit programUnit, final ReportSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ReportDescription addReportDescription(final ReportDescriptionContext ctx) {
		ReportDescription result = (ReportDescription) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ReportDescriptionImpl(name, programUnit, ctx);

			/*
			 * report description entry
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

			reportDescriptions.add(result);
			reportDescriptionsSymbolTable.put(getSymbol(name), result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ReportDescription getReportDescription(final String name) {
		return reportDescriptionsSymbolTable.get(getSymbol(name));
	}

	@Override
	public List<ReportDescription> getReportDescriptions() {
		return reportDescriptions;
	}

}
