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

import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.CobolParser.ReportGroupLineNumberClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupLineNumberNextPageContext;
import io.proleap.cobol.CobolParser.ReportGroupLineNumberPlusContext;
import io.proleap.cobol.CobolParser.ReportGroupUsageClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.report.LineNumberClause;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.report.UsageClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public abstract class ReportGroupDescriptionEntryImpl extends CobolDivisionElementImpl
		implements ReportGroupDescriptionEntry {

	private final static Logger LOG = LoggerFactory.getLogger(ReportGroupDescriptionEntryImpl.class);

	protected final ParserRuleContext ctx;

	protected Integer levelNumber;

	protected LineNumberClause lineNumberClause;

	protected final String name;

	protected ReportGroupDescriptionEntry parentReportGroupDescriptionEntry;

	protected List<ReportGroupDescriptionEntry> reportGroupDescriptionEntries = new ArrayList<ReportGroupDescriptionEntry>();

	protected Map<String, ReportGroupDescriptionEntry> reportGroupDescriptionEntriesSymbolTable = new HashMap<String, ReportGroupDescriptionEntry>();

	protected UsageClause usageClause;

	public ReportGroupDescriptionEntryImpl(final String name, final ProgramUnit programUnit,
			final ParserRuleContext ctx) {
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
			final UsageClause.UsageClauseType type;

			if (ctx.DISPLAY() != null) {
				type = UsageClause.UsageClauseType.DISPLAY;
			} else if (ctx.DISPLAY_1() != null) {
				type = UsageClause.UsageClauseType.DISPLAY_1;
			} else {
				LOG.warn("unknown usage at {}", ctx);
				type = null;
			}

			result.setUsageClauseType(type);

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

			final LineNumberClause.LineNumberClauseType type;
			final IntegerLiteral integerLiteral;

			if (ctx.reportGroupLineNumberNextPage() != null) {
				type = LineNumberClause.LineNumberClauseType.NEXT_PAGE;
				final ReportGroupLineNumberNextPageContext reportGroupLineNumberNextPage = ctx
						.reportGroupLineNumberNextPage();

				if (reportGroupLineNumberNextPage.integerLiteral() != null) {
					integerLiteral = createIntegerLiteral(reportGroupLineNumberNextPage.integerLiteral());
				} else {
					integerLiteral = null;
				}
			} else if (ctx.reportGroupLineNumberPlus() != null) {
				type = LineNumberClause.LineNumberClauseType.PLUS;
				final ReportGroupLineNumberPlusContext reportGroupLineNumberPlus = ctx.reportGroupLineNumberPlus();

				if (reportGroupLineNumberPlus.integerLiteral() != null) {
					integerLiteral = createIntegerLiteral(reportGroupLineNumberPlus.integerLiteral());
				} else {
					integerLiteral = null;
				}
			} else {
				LOG.warn("unknown line number at {}", ctx);
				type = null;
				integerLiteral = null;
			}

			result.setLineNumberClauseType(type);
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
		reportGroupDescriptionEntriesSymbolTable.put(getSymbol(name), reportGroupDescriptionEntry);
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
		return reportGroupDescriptionEntriesSymbolTable.get(getSymbol(name));
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
