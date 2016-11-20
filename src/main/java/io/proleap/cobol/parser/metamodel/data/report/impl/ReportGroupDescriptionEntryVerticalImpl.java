/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.ReportGroupDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.ReportGroupNextGroupClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupTypeClauseContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.NextGroupClause;
import io.proleap.cobol.parser.metamodel.data.report.ReportGroupDescriptionEntryVertical;
import io.proleap.cobol.parser.metamodel.data.report.TypeClause;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class ReportGroupDescriptionEntryVerticalImpl extends ReportGroupDescriptionEntryImpl
		implements ReportGroupDescriptionEntryVertical {

	private final static Logger LOG = LogManager.getLogger(ReportGroupDescriptionEntryVerticalImpl.class);

	protected final ReportGroupDescriptionEntryFormat1Context ctx;

	protected NextGroupClause nextGroupClause;

	protected TypeClause typeClause;

	public ReportGroupDescriptionEntryVerticalImpl(final String name, final ProgramUnit programUnit,
			final ReportGroupDescriptionEntryFormat1Context ctx) {
		super(name, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public NextGroupClause addNextGroupClause(final ReportGroupNextGroupClauseContext ctx) {
		NextGroupClause result = (NextGroupClause) getASGElement(ctx);

		if (result == null) {
			result = new NextGroupClauseImpl(programUnit, ctx);

			/*
			 * type and integerLiteral
			 */
			final NextGroupClause.Type type;
			final IntegerLiteral integerLiteral;

			if (ctx.reportGroupNextGroupNextPage() != null) {
				type = NextGroupClause.Type.NextPage;
				integerLiteral = null;
			} else if (ctx.reportGroupNextGroupPlus() != null) {
				type = NextGroupClause.Type.Plus;
				integerLiteral = addIntegerLiteral(ctx.reportGroupNextGroupPlus().integerLiteral());
			} else {
				type = NextGroupClause.Type.Absolute;
				integerLiteral = addIntegerLiteral(ctx.integerLiteral());
			}

			result.setIntegerLiteral(integerLiteral);
			result.setType(type);

			nextGroupClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public TypeClause addTypeClause(final ReportGroupTypeClauseContext ctx) {
		TypeClause result = (TypeClause) getASGElement(ctx);

		if (result == null) {
			result = new TypeClauseImpl(programUnit, ctx);

			/*
			 * type and data
			 */
			final TypeClause.Type type;
			final ValueStmt dataValueStmt;

			if (ctx.reportGroupTypeReportHeading() != null) {
				type = TypeClause.Type.ReportHeading;
				dataValueStmt = null;
			} else if (ctx.reportGroupTypePageHeading() != null) {
				type = TypeClause.Type.PageHeading;
				dataValueStmt = null;
			} else if (ctx.reportGroupTypeControlHeading() != null) {
				type = TypeClause.Type.ControlHeading;
				dataValueStmt = createCallValueStmt(ctx.reportGroupTypeControlHeading().dataName());
			} else if (ctx.reportGroupTypeDetail() != null) {
				type = TypeClause.Type.Detail;
				dataValueStmt = null;
			} else if (ctx.reportGroupTypeControlFooting() != null) {
				type = TypeClause.Type.ControlFooting;
				dataValueStmt = createCallValueStmt(ctx.reportGroupTypeControlFooting().dataName());
			} else if (ctx.reportGroupTypePageFooting() != null) {
				type = TypeClause.Type.PageFooting;
				dataValueStmt = null;
			} else if (ctx.reportGroupTypeReportFooting() != null) {
				type = TypeClause.Type.ReportFooting;
				dataValueStmt = null;
			} else {
				type = null;
				dataValueStmt = null;
			}

			result.setType(type);
			result.setDataValueStmt(dataValueStmt);

			typeClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public NextGroupClause getNextGroupClause() {
		return nextGroupClause;
	}

	@Override
	public Type getType() {
		return Type.Vertical;
	}

	@Override
	public TypeClause getTypeClause() {
		return typeClause;
	}

}
