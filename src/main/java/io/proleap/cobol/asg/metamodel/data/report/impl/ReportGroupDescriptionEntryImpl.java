/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.ReportGroupLineNumberClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupUsageClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.report.LineNumberClause;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.report.UsageClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public abstract class ReportGroupDescriptionEntryImpl extends CobolDivisionElementImpl
		implements ReportGroupDescriptionEntry {

	private final static Logger LOG = LogManager.getLogger(ReportGroupDescriptionEntryImpl.class);

	protected final ParseTree ctx;

	protected Integer levelNumber;

	protected LineNumberClause lineNumberClause;

	protected final String name;

	protected ReportGroupDescriptionEntry parentReportGroupDescriptionEntry;

	protected List<ReportGroupDescriptionEntry> reportGroupDescriptionEntries = new ArrayList<ReportGroupDescriptionEntry>();

	protected Map<String, ReportGroupDescriptionEntry> reportGroupDescriptionEntriesSymbolTable = new HashMap<String, ReportGroupDescriptionEntry>();

	protected UsageClause usageClause;

	public ReportGroupDescriptionEntryImpl(final String name, final ProgramUnit programUnit, final ParseTree ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public UsageClause addGroupUsageClause(final ReportGroupUsageClauseContext ctx) {
		UsageClause result = (UsageClause) getASGElement(ctx);

		if (result == null) {
			result = new UsageClauseImpl(programUnit, ctx);

			/*
			 * type
			 */
			final UsageClause.Type type;

			if (ctx.DISPLAY() != null) {
				type = UsageClause.Type.DISPLAY;
			} else if (ctx.DISPLAY_1() != null) {
				type = UsageClause.Type.DISPLAY_1;
			} else {
				LOG.warn("unknown usage at {}", ctx);
				type = null;
			}

			result.setType(type);

			usageClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public LineNumberClause addLineNumberClause(final ReportGroupLineNumberClauseContext ctx) {
		LineNumberClause result = (LineNumberClause) getASGElement(ctx);

		if (result == null) {
			result = new LineNumberClauseImpl(programUnit, ctx);

			final LineNumberClause.Type type;
			final IntegerLiteral integerLiteral;

			if (ctx.reportGroupLineNumberNextPage() != null) {
				type = LineNumberClause.Type.NEXT_PAGE;
				integerLiteral = createIntegerLiteral(ctx.reportGroupLineNumberNextPage().integerLiteral());
			} else if (ctx.reportGroupLineNumberPlus() != null) {
				type = LineNumberClause.Type.PLUS;
				integerLiteral = createIntegerLiteral(ctx.reportGroupLineNumberPlus().integerLiteral());
			} else {
				LOG.warn("unknown line number at {}", ctx);
				type = null;
				integerLiteral = null;
			}

			result.setType(type);
			result.setIntegerLiteral(integerLiteral);

			lineNumberClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public void addReportGroupDescriptionEntry(final ReportGroupDescriptionEntry reportGroupDescriptionEntry) {
		final String name = reportGroupDescriptionEntry.getName();

		reportGroupDescriptionEntries.add(reportGroupDescriptionEntry);
		reportGroupDescriptionEntriesSymbolTable.put(name, reportGroupDescriptionEntry);
	}

	@Override
	public Integer getLevelNumber() {
		return levelNumber;
	}

	@Override
	public LineNumberClause getLineNumberClause() {
		return lineNumberClause;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ReportGroupDescriptionEntry getParentReportGroupDescriptionEntry() {
		return parentReportGroupDescriptionEntry;
	}

	@Override
	public List<ReportGroupDescriptionEntry> getReportGroupDescriptionEntries() {
		return reportGroupDescriptionEntries;
	}

	@Override
	public ReportGroupDescriptionEntry getReportGroupDescriptionEntry(final String name) {
		return reportGroupDescriptionEntriesSymbolTable.get(name);
	}

	@Override
	public UsageClause getUsageClause() {
		return usageClause;
	}

	@Override
	public void setLevelNumber(final Integer levelNumber) {
		this.levelNumber = levelNumber;
	}

	@Override
	public void setParentReportGroupDescriptionEntry(
			final ReportGroupDescriptionEntry parentReportGroupDescriptionEntry) {
		this.parentReportGroupDescriptionEntry = parentReportGroupDescriptionEntry;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}

}
