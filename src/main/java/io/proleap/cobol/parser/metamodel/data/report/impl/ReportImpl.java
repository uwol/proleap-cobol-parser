/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.ReportContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.ReportGroupDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.ReportGroupDescriptionEntryFormat3Context;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.Report;
import io.proleap.cobol.parser.metamodel.data.report.ReportDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.report.ReportGroupDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.report.ReportGroupDescriptionEntryPrintable;
import io.proleap.cobol.parser.metamodel.data.report.ReportGroupDescriptionEntrySingle;
import io.proleap.cobol.parser.metamodel.data.report.ReportGroupDescriptionEntryVertical;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class ReportImpl extends CobolDivisionElementImpl implements Report {

	private final static Logger LOG = LogManager.getLogger(ReportImpl.class);

	protected final ReportContext ctx;

	protected String name;

	protected ReportDescriptionEntry reportDescriptionEntry;

	protected List<ReportGroupDescriptionEntry> reportGroupDescriptionEntries = new ArrayList<ReportGroupDescriptionEntry>();

	public ReportImpl(final String name, final ProgramUnit programUnit, final ReportContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public ReportDescriptionEntry addReportDescriptionEntry(final ReportDescriptionEntryContext ctx) {
		ReportDescriptionEntry result = (ReportDescriptionEntry) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx.reportName());
			result = new ReportDescriptionEntryImpl(name, programUnit, ctx);

			/*
			 * global
			 */
			if (ctx.reportDescriptionGlobalClause() != null) {
				result.addGlobalClause(ctx.reportDescriptionGlobalClause());
			}

			/*
			 * page limit
			 */
			if (ctx.reportDescriptionPageLimitClause() != null) {
				result.addPageLimitClause(ctx.reportDescriptionPageLimitClause());
			}

			/*
			 * heading
			 */
			if (ctx.reportDescriptionHeadingClause() != null) {
				result.addHeadingClause(ctx.reportDescriptionHeadingClause());
			}

			/*
			 * first detail
			 */
			if (ctx.reportDescriptionFirstDetailClause() != null) {
				result.addFirstDetailClause(ctx.reportDescriptionFirstDetailClause());
			}

			/*
			 * last detail
			 */
			if (ctx.reportDescriptionLastDetailClause() != null) {
				result.addLastDetailClause(ctx.reportDescriptionLastDetailClause());
			}

			/*
			 * heading
			 */
			if (ctx.reportDescriptionFootingClause() != null) {
				result.addFootingClause(ctx.reportDescriptionFootingClause());
			}

			reportDescriptionEntry = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ReportGroupDescriptionEntryPrintable addReportGroupDescriptionEntryPrintable(
			final ReportGroupDescriptionEntryFormat3Context ctx) {
		// FIXME
		return null;
	}

	@Override
	public ReportGroupDescriptionEntrySingle addReportGroupDescriptionEntrySingle(
			final ReportGroupDescriptionEntryFormat2Context ctx) {
		// FIXME
		return null;
	}

	@Override
	public ReportGroupDescriptionEntryVertical addReportGroupDescriptionEntryVertical(
			final ReportGroupDescriptionEntryFormat1Context ctx) {
		// FIXME
		return null;
	}

	@Override
	public ReportGroupDescriptionEntry createReportGroupDescriptionEntry(final ReportGroupDescriptionEntryContext ctx) {
		final ReportGroupDescriptionEntry result;

		if (ctx.reportGroupDescriptionEntryFormat1() != null) {
			result = addReportGroupDescriptionEntryVertical(ctx.reportGroupDescriptionEntryFormat1());
		} else if (ctx.reportGroupDescriptionEntryFormat2() != null) {
			result = addReportGroupDescriptionEntrySingle(ctx.reportGroupDescriptionEntryFormat2());
		} else if (ctx.reportGroupDescriptionEntryFormat3() != null) {
			result = addReportGroupDescriptionEntryPrintable(ctx.reportGroupDescriptionEntryFormat3());
		} else {
			LOG.warn("unknown report description entry {}", ctx);
			result = null;
		}

		return result;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ReportDescriptionEntry getReportDescriptionEntry() {
		return reportDescriptionEntry;
	}

	@Override
	public List<ReportGroupDescriptionEntry> getReportGroupDescriptionEntries() {
		return reportGroupDescriptionEntries;
	}

}
