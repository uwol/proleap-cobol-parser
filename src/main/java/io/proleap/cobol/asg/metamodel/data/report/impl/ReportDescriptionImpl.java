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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.CobolParser.ReportDescriptionContext;
import io.proleap.cobol.CobolParser.ReportDescriptionEntryContext;
import io.proleap.cobol.CobolParser.ReportGroupBlankWhenZeroClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupColumnNumberClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupDescriptionEntryContext;
import io.proleap.cobol.CobolParser.ReportGroupDescriptionEntryFormat1Context;
import io.proleap.cobol.CobolParser.ReportGroupDescriptionEntryFormat2Context;
import io.proleap.cobol.CobolParser.ReportGroupDescriptionEntryFormat3Context;
import io.proleap.cobol.CobolParser.ReportGroupIndicateClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupJustifiedClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupLineNumberClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupNextGroupClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupPictureClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupResetClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupSignClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupSourceClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupSumClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupTypeClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupUsageClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupValueClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.ReportCall;
import io.proleap.cobol.asg.metamodel.data.report.ReportDescription;
import io.proleap.cobol.asg.metamodel.data.report.ReportDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntriesSymbolTableEntry;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntryPrintable;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntrySingle;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntryVertical;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.util.AsgStringUtils;

public class ReportDescriptionImpl extends CobolDivisionElementImpl implements ReportDescription {

	private final static Logger LOG = LoggerFactory.getLogger(ReportDescriptionImpl.class);

	protected List<ReportCall> calls = new ArrayList<ReportCall>();

	protected final ReportDescriptionContext ctx;

	protected String name;

	protected ReportDescriptionEntry reportDescriptionEntry;

	protected List<ReportGroupDescriptionEntry> reportGroupDescriptionEntries = new ArrayList<ReportGroupDescriptionEntry>();

	protected Map<String, ReportGroupDescriptionEntriesSymbolTableEntry> reportGroupDescriptionEntriesSymbolTable = new HashMap<String, ReportGroupDescriptionEntriesSymbolTableEntry>();

	public ReportDescriptionImpl(final String name, final ProgramUnit programUnit, final ReportDescriptionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public void addCall(final ReportCall call) {
		calls.add(call);
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

			result.setReport(this);
			reportDescriptionEntry = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ReportGroupDescriptionEntryPrintable addReportGroupDescriptionEntryPrintable(
			final ReportGroupDescriptionEntryFormat3Context ctx) {
		ReportGroupDescriptionEntryPrintable result = (ReportGroupDescriptionEntryPrintable) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ReportGroupDescriptionEntryPrintableImpl(name, programUnit, ctx);

			/*
			 * level number
			 */
			final Integer levelNumber = AsgStringUtils.parseInteger(ctx.integerLiteral().getText());
			result.setLevelNumber(levelNumber);

			/*
			 * picture
			 */
			final List<ReportGroupPictureClauseContext> pictureClauseContexts = ctx.reportGroupPictureClause();

			if (!pictureClauseContexts.isEmpty()) {
				final ReportGroupPictureClauseContext pictureClauseContext = pictureClauseContexts.get(0);
				result.addPictureClause(pictureClauseContext);
			}

			/*
			 * usage
			 */
			final List<ReportGroupUsageClauseContext> usageClauseContexts = ctx.reportGroupUsageClause();

			if (!usageClauseContexts.isEmpty()) {
				final ReportGroupUsageClauseContext groupUsageClause = usageClauseContexts.get(0);
				result.addGroupUsageClause(groupUsageClause);
			}

			/*
			 * sign
			 */
			final List<ReportGroupSignClauseContext> signClauseContexts = ctx.reportGroupSignClause();

			if (!signClauseContexts.isEmpty()) {
				final ReportGroupSignClauseContext signClauseContext = signClauseContexts.get(0);
				result.addSignClause(signClauseContext);
			}

			/*
			 * justified
			 */
			final List<ReportGroupJustifiedClauseContext> justifiedClauseContexts = ctx.reportGroupJustifiedClause();

			if (!justifiedClauseContexts.isEmpty()) {
				final ReportGroupJustifiedClauseContext justifiedClauseContext = justifiedClauseContexts.get(0);
				result.addJustifiedClause(justifiedClauseContext);
			}

			/*
			 * blank when zero
			 */
			final List<ReportGroupBlankWhenZeroClauseContext> blankWhenZeroClauseContexts = ctx
					.reportGroupBlankWhenZeroClause();

			if (!blankWhenZeroClauseContexts.isEmpty()) {
				final ReportGroupBlankWhenZeroClauseContext blankWhenZeroClauseContext = blankWhenZeroClauseContexts
						.get(0);
				result.addBlankWhenZeroClause(blankWhenZeroClauseContext);
			}

			/*
			 * line number
			 */
			final List<ReportGroupLineNumberClauseContext> lineNumberClauseContexts = ctx.reportGroupLineNumberClause();

			if (!lineNumberClauseContexts.isEmpty()) {
				final ReportGroupLineNumberClauseContext lineNumberClauseContext = lineNumberClauseContexts.get(0);
				result.addLineNumberClause(lineNumberClauseContext);
			}

			/*
			 * column number
			 */
			final List<ReportGroupColumnNumberClauseContext> columnNumberClauseContexts = ctx
					.reportGroupColumnNumberClause();

			if (!columnNumberClauseContexts.isEmpty()) {
				final ReportGroupColumnNumberClauseContext columnNumberClauseContext = columnNumberClauseContexts
						.get(0);
				result.addColumnNumberClause(columnNumberClauseContext);
			}

			/*
			 * source
			 */
			final List<ReportGroupSourceClauseContext> sourceClauseContexts = ctx.reportGroupSourceClause();

			if (!sourceClauseContexts.isEmpty()) {
				final ReportGroupSourceClauseContext sourceClauseContext = sourceClauseContexts.get(0);
				result.addSourceClause(sourceClauseContext);
			}

			/*
			 * value
			 */
			final List<ReportGroupValueClauseContext> valueClauseContexts = ctx.reportGroupValueClause();

			if (!valueClauseContexts.isEmpty()) {
				final ReportGroupValueClauseContext valueClauseContext = valueClauseContexts.get(0);
				result.addValueClause(valueClauseContext);
			}

			/*
			 * sum
			 */
			final List<ReportGroupSumClauseContext> sumClauseContexts = ctx.reportGroupSumClause();

			if (!sumClauseContexts.isEmpty()) {
				final ReportGroupSumClauseContext sumClauseContext = sumClauseContexts.get(0);
				result.addSumClause(sumClauseContext);
			}

			/*
			 * reset
			 */
			final List<ReportGroupResetClauseContext> resetClauseContexts = ctx.reportGroupResetClause();

			if (!resetClauseContexts.isEmpty()) {
				final ReportGroupResetClauseContext resetClauseContext = resetClauseContexts.get(0);
				result.addResetClause(resetClauseContext);
			}

			/*
			 * group indicate
			 */
			final List<ReportGroupIndicateClauseContext> indicateClauseContexts = ctx.reportGroupIndicateClause();

			if (!indicateClauseContexts.isEmpty()) {
				final ReportGroupIndicateClauseContext indicateClauseContext = indicateClauseContexts.get(0);
				result.addGroupIndicateClause(indicateClauseContext);
			}

			reportGroupDescriptionEntries.add(result);
			assureReportGroupDescriptionEntriesSymbolTableEntry(name).addReportGroupDescriptionEntry(result);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ReportGroupDescriptionEntrySingle addReportGroupDescriptionEntrySingle(
			final ReportGroupDescriptionEntryFormat2Context ctx) {
		ReportGroupDescriptionEntrySingle result = (ReportGroupDescriptionEntrySingle) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ReportGroupDescriptionEntrySingleImpl(name, programUnit, ctx);

			/*
			 * level number
			 */
			final Integer levelNumber = AsgStringUtils.parseInteger(ctx.integerLiteral().getText());
			result.setLevelNumber(levelNumber);

			/*
			 * line number
			 */
			if (ctx.reportGroupLineNumberClause() != null) {
				final ReportGroupLineNumberClauseContext lineNumberClauseContext = ctx.reportGroupLineNumberClause();
				result.addLineNumberClause(lineNumberClauseContext);
			}

			/*
			 * group usage
			 */
			if (ctx.reportGroupUsageClause() != null) {
				final ReportGroupUsageClauseContext usageClauseContext = ctx.reportGroupUsageClause();
				result.addGroupUsageClause(usageClauseContext);
			}

			reportGroupDescriptionEntries.add(result);
			assureReportGroupDescriptionEntriesSymbolTableEntry(name).addReportGroupDescriptionEntry(result);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ReportGroupDescriptionEntryVertical addReportGroupDescriptionEntryVertical(
			final ReportGroupDescriptionEntryFormat1Context ctx) {
		ReportGroupDescriptionEntryVertical result = (ReportGroupDescriptionEntryVertical) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ReportGroupDescriptionEntryVerticalImpl(name, programUnit, ctx);

			/*
			 * level number
			 */
			final Integer levelNumber = AsgStringUtils.parseInteger(ctx.integerLiteral().getText());
			result.setLevelNumber(levelNumber);

			/*
			 * line number
			 */
			if (ctx.reportGroupLineNumberClause() != null) {
				final ReportGroupLineNumberClauseContext lineNumberClauseContext = ctx.reportGroupLineNumberClause();
				result.addLineNumberClause(lineNumberClauseContext);
			}

			/*
			 * next group
			 */
			if (ctx.reportGroupNextGroupClause() != null) {
				final ReportGroupNextGroupClauseContext nextGroupClause = ctx.reportGroupNextGroupClause();
				result.addNextGroupClause(nextGroupClause);
			}

			/*
			 * type
			 */
			if (ctx.reportGroupTypeClause() != null) {
				final ReportGroupTypeClauseContext typeClause = ctx.reportGroupTypeClause();
				result.addTypeClause(typeClause);
			}

			/*
			 * usage
			 */
			if (ctx.reportGroupUsageClause() != null) {
				final ReportGroupUsageClauseContext groupUsageClause = ctx.reportGroupUsageClause();
				result.addGroupUsageClause(groupUsageClause);
			}

			reportGroupDescriptionEntries.add(result);
			assureReportGroupDescriptionEntriesSymbolTableEntry(name).addReportGroupDescriptionEntry(result);

			registerASGElement(result);
		}

		return result;
	}

	protected ReportGroupDescriptionEntriesSymbolTableEntry assureReportGroupDescriptionEntriesSymbolTableEntry(
			final String name) {
		ReportGroupDescriptionEntriesSymbolTableEntry reportGroupDescriptionEntriesSymbolTableEntry = reportGroupDescriptionEntriesSymbolTable
				.get(getSymbol(name));

		if (reportGroupDescriptionEntriesSymbolTableEntry == null) {
			reportGroupDescriptionEntriesSymbolTableEntry = new ReportGroupDescriptionEntriesSymbolTableEntryImpl();
			reportGroupDescriptionEntriesSymbolTable.put(getSymbol(name),
					reportGroupDescriptionEntriesSymbolTableEntry);
		}

		return reportGroupDescriptionEntriesSymbolTableEntry;
	}

	@Override
	public ReportGroupDescriptionEntry createReportGroupDescriptionEntry(
			final ReportGroupDescriptionEntry lastReportGroupDescriptionEntry,
			final ReportGroupDescriptionEntryContext ctx) {
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

		if (lastReportGroupDescriptionEntry != null && result != null) {
			groupReportGroupDescriptionEntry(lastReportGroupDescriptionEntry, result);
		}

		return result;
	}

	@Override
	public List<ReportCall> getCalls() {
		return calls;
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

	@Override
	public List<ReportGroupDescriptionEntry> getReportGroupDescriptionEntries(final String name) {
		return reportGroupDescriptionEntriesSymbolTable.get(getSymbol(name)) == null ? null
				: reportGroupDescriptionEntriesSymbolTable.get(getSymbol(name)).getReportGroupDescriptionEntries();
	}

	@Override
	public ReportGroupDescriptionEntry getReportGroupDescriptionEntry(final String name) {
		return reportGroupDescriptionEntriesSymbolTable.get(getSymbol(name)) == null ? null
				: reportGroupDescriptionEntriesSymbolTable.get(getSymbol(name)).getReportGroupDescriptionEntry();
	}

	@Override
	public List<ReportGroupDescriptionEntry> getRootReportGroupDescriptionEntries() {
		final List<ReportGroupDescriptionEntry> result = new ArrayList<ReportGroupDescriptionEntry>();

		for (final ReportGroupDescriptionEntry reportGroupDescriptionEntry : reportGroupDescriptionEntries) {

			if (reportGroupDescriptionEntry.getParentReportGroupDescriptionEntry() == null) {
				result.add(reportGroupDescriptionEntry);
			}
		}

		return result;
	}

	protected void groupReportGroupDescriptionEntry(final ReportGroupDescriptionEntry lastReportGroupDescriptionEntry,
			final ReportGroupDescriptionEntry reportGroupDescriptionEntry) {
		final Integer lastLevelNumber = lastReportGroupDescriptionEntry.getLevelNumber();
		final Integer levelNumber = reportGroupDescriptionEntry.getLevelNumber();

		if (levelNumber > lastLevelNumber) {
			lastReportGroupDescriptionEntry.addReportGroupDescriptionEntry(reportGroupDescriptionEntry);
			reportGroupDescriptionEntry.setParentReportGroupDescriptionEntry(lastReportGroupDescriptionEntry);
		} else {
			final ReportGroupDescriptionEntry lastParentReportGroupDescriptionEntry = lastReportGroupDescriptionEntry
					.getParentReportGroupDescriptionEntry();

			if (lastParentReportGroupDescriptionEntry != null) {
				groupReportGroupDescriptionEntry(lastParentReportGroupDescriptionEntry, reportGroupDescriptionEntry);
			}
		}
	}
}
