/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.ReportDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionFirstDetailClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionFootingClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionGlobalClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionHeadingClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionLastDetailClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportDescriptionPageLimitClauseContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.FirstDetailClause;
import io.proleap.cobol.parser.metamodel.data.report.FootingClause;
import io.proleap.cobol.parser.metamodel.data.report.GlobalClause;
import io.proleap.cobol.parser.metamodel.data.report.HeadingClause;
import io.proleap.cobol.parser.metamodel.data.report.LastDetailClause;
import io.proleap.cobol.parser.metamodel.data.report.PageLimitClause;
import io.proleap.cobol.parser.metamodel.data.report.ReportDescriptionEntry;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class ReportDescriptionEntryImpl extends CobolDivisionElementImpl implements ReportDescriptionEntry {

	protected final ReportDescriptionEntryContext ctx;

	protected FirstDetailClause firstDetailClause;

	protected FootingClause footingClause;

	protected GlobalClause globalClause;

	protected HeadingClause headingClause;

	protected LastDetailClause lastDetailClause;

	protected final String name;

	protected PageLimitClause pageLimitClause;

	public ReportDescriptionEntryImpl(final String name, final ProgramUnit programUnit,
			final ReportDescriptionEntryContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public FirstDetailClause addFirstDetailClause(final ReportDescriptionFirstDetailClauseContext ctx) {
		FirstDetailClause result = (FirstDetailClause) getASGElement(ctx);

		if (result == null) {
			result = new FirstDetailClauseImpl(programUnit, ctx);

			final IntegerLiteral firstDetailIntegerLiteral = addIntegerLiteral(ctx.integerLiteral());
			result.setFirstDetailIntegerLiteral(firstDetailIntegerLiteral);

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

			final IntegerLiteral footingIntegerLiteral = addIntegerLiteral(ctx.integerLiteral());
			result.setFootingIntegerLiteral(footingIntegerLiteral);

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

			final IntegerLiteral headingIntegerLiteral = addIntegerLiteral(ctx.integerLiteral());
			result.setHeadingIntegerLiteral(headingIntegerLiteral);

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

			final IntegerLiteral lastDetailIntegerLiteral = addIntegerLiteral(ctx.integerLiteral());
			result.setLastDetailIntegerLiteral(lastDetailIntegerLiteral);

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

			final IntegerLiteral pageLimitIntegerLiteral = addIntegerLiteral(ctx.integerLiteral());
			result.setPageLimitIntegerLiteral(pageLimitIntegerLiteral);

			pageLimitClause = result;
			registerASGElement(result);
		}

		return result;
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

}
