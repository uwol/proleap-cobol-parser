/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.ReportDescriptionEntryContext;
import io.proleap.cobol.CobolParser.ReportDescriptionFirstDetailClauseContext;
import io.proleap.cobol.CobolParser.ReportDescriptionFootingClauseContext;
import io.proleap.cobol.CobolParser.ReportDescriptionGlobalClauseContext;
import io.proleap.cobol.CobolParser.ReportDescriptionHeadingClauseContext;
import io.proleap.cobol.CobolParser.ReportDescriptionLastDetailClauseContext;
import io.proleap.cobol.CobolParser.ReportDescriptionPageLimitClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.ReportDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.report.FirstDetailClause;
import io.proleap.cobol.asg.metamodel.data.report.FootingClause;
import io.proleap.cobol.asg.metamodel.data.report.GlobalClause;
import io.proleap.cobol.asg.metamodel.data.report.HeadingClause;
import io.proleap.cobol.asg.metamodel.data.report.LastDetailClause;
import io.proleap.cobol.asg.metamodel.data.report.PageLimitClause;
import io.proleap.cobol.asg.metamodel.data.report.ReportDescription;
import io.proleap.cobol.asg.metamodel.data.report.ReportDescriptionEntry;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ReportDescriptionEntryImpl extends CobolDivisionElementImpl implements ReportDescriptionEntry {

	protected List<ReportDescriptionEntryCall> calls = new ArrayList<ReportDescriptionEntryCall>();

	protected final ReportDescriptionEntryContext ctx;

	protected FirstDetailClause firstDetailClause;

	protected FootingClause footingClause;

	protected GlobalClause globalClause;

	protected HeadingClause headingClause;

	protected LastDetailClause lastDetailClause;

	protected final String name;

	protected PageLimitClause pageLimitClause;

	protected ReportDescription report;

	public ReportDescriptionEntryImpl(final String name, final ProgramUnit programUnit,
			final ReportDescriptionEntryContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public void addCall(final ReportDescriptionEntryCall call) {
		calls.add(call);
	}

	@Override
	public FirstDetailClause addFirstDetailClause(final ReportDescriptionFirstDetailClauseContext ctx) {
		FirstDetailClause result = (FirstDetailClause) getASGElement(ctx);

		if (result == null) {
			result = new FirstDetailClauseImpl(programUnit, ctx);

			if (ctx.integerLiteral() != null) {
				final IntegerLiteral firstDetailIntegerLiteral = createIntegerLiteral(ctx.integerLiteral());
				result.setFirstDetailIntegerLiteral(firstDetailIntegerLiteral);
			}

			firstDetailClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public FootingClause addFootingClause(final ReportDescriptionFootingClauseContext ctx) {
		FootingClause result = (FootingClause) getASGElement(ctx);

		if (result == null) {
			result = new FootingClauseImpl(programUnit, ctx);

			if (ctx.integerLiteral() != null) {
				final IntegerLiteral footingIntegerLiteral = createIntegerLiteral(ctx.integerLiteral());
				result.setFootingIntegerLiteral(footingIntegerLiteral);
			}

			footingClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public GlobalClause addGlobalClause(final ReportDescriptionGlobalClauseContext ctx) {
		GlobalClause result = (GlobalClause) getASGElement(ctx);

		if (result == null) {
			result = new GlobalClauseImpl(programUnit, ctx);

			result.setGlobal(true);

			globalClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public HeadingClause addHeadingClause(final ReportDescriptionHeadingClauseContext ctx) {
		HeadingClause result = (HeadingClause) getASGElement(ctx);

		if (result == null) {
			result = new HeadingClauseImpl(programUnit, ctx);

			if (ctx.integerLiteral() != null) {
				final IntegerLiteral headingIntegerLiteral = createIntegerLiteral(ctx.integerLiteral());
				result.setHeadingIntegerLiteral(headingIntegerLiteral);
			}

			headingClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public LastDetailClause addLastDetailClause(final ReportDescriptionLastDetailClauseContext ctx) {
		LastDetailClause result = (LastDetailClause) getASGElement(ctx);

		if (result == null) {
			result = new LastDetailClauseImpl(programUnit, ctx);

			if (ctx.integerLiteral() != null) {
				final IntegerLiteral lastDetailIntegerLiteral = createIntegerLiteral(ctx.integerLiteral());
				result.setLastDetailIntegerLiteral(lastDetailIntegerLiteral);
			}

			lastDetailClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PageLimitClause addPageLimitClause(final ReportDescriptionPageLimitClauseContext ctx) {
		PageLimitClause result = (PageLimitClause) getASGElement(ctx);

		if (result == null) {
			result = new PageLimitClauseImpl(programUnit, ctx);

			if (ctx.integerLiteral() != null) {
				final IntegerLiteral pageLimitIntegerLiteral = createIntegerLiteral(ctx.integerLiteral());
				result.setPageLimitIntegerLiteral(pageLimitIntegerLiteral);
			}

			pageLimitClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<ReportDescriptionEntryCall> getCalls() {
		return calls;
	}

	@Override
	public FirstDetailClause getFirstDetailClause() {
		return firstDetailClause;
	}

	@Override
	public FootingClause getFootingClause() {
		return footingClause;
	}

	@Override
	public GlobalClause getGlobalClause() {
		return globalClause;
	}

	@Override
	public HeadingClause getHeadingClause() {
		return headingClause;
	}

	@Override
	public LastDetailClause getLastDetailClause() {
		return lastDetailClause;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public PageLimitClause getPageLimitClause() {
		return pageLimitClause;
	}

	@Override
	public ReportDescription getReport() {
		return report;
	}

	@Override
	public void setReport(final ReportDescription report) {
		this.report = report;
	}

}
