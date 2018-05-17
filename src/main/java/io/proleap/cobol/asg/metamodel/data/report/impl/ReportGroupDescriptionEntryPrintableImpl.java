/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import io.proleap.cobol.CobolParser.DataNameContext;
import io.proleap.cobol.CobolParser.IdentifierContext;
import io.proleap.cobol.CobolParser.PictureStringContext;
import io.proleap.cobol.CobolParser.ReportGroupBlankWhenZeroClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupColumnNumberClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupDescriptionEntryFormat3Context;
import io.proleap.cobol.CobolParser.ReportGroupIndicateClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupJustifiedClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupPictureClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupResetClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupSignClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupSourceClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupSumClauseContext;
import io.proleap.cobol.CobolParser.ReportGroupValueClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.report.BlankWhenZeroClause;
import io.proleap.cobol.asg.metamodel.data.report.ColumnNumberClause;
import io.proleap.cobol.asg.metamodel.data.report.GroupIndicateClause;
import io.proleap.cobol.asg.metamodel.data.report.JustifiedClause;
import io.proleap.cobol.asg.metamodel.data.report.PictureClause;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntryPrintable;
import io.proleap.cobol.asg.metamodel.data.report.ResetClause;
import io.proleap.cobol.asg.metamodel.data.report.SignClause;
import io.proleap.cobol.asg.metamodel.data.report.SourceClause;
import io.proleap.cobol.asg.metamodel.data.report.SumClause;
import io.proleap.cobol.asg.metamodel.data.report.ValueClause;

public class ReportGroupDescriptionEntryPrintableImpl extends ReportGroupDescriptionEntryImpl
		implements ReportGroupDescriptionEntryPrintable {

	protected BlankWhenZeroClause blankWhenZeroClause;

	protected ColumnNumberClause columnNumberClause;

	protected final ReportGroupDescriptionEntryFormat3Context ctx;

	protected GroupIndicateClause groupIndicateClause;

	protected JustifiedClause justifiedClause;

	protected PictureClause pictureClause;

	protected ResetClause resetClause;

	protected SignClause signClause;

	protected SourceClause sourceClause;

	protected SumClause sumClause;

	protected ValueClause valueClause;

	public ReportGroupDescriptionEntryPrintableImpl(final String name, final ProgramUnit programUnit,
			final ReportGroupDescriptionEntryFormat3Context ctx) {
		super(name, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public BlankWhenZeroClause addBlankWhenZeroClause(final ReportGroupBlankWhenZeroClauseContext ctx) {
		BlankWhenZeroClause result = (BlankWhenZeroClause) getASGElement(ctx);

		if (result == null) {
			result = new BlankWhenZeroClauseImpl(programUnit, ctx);

			result.setBlankWhenZero(true);

			blankWhenZeroClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ColumnNumberClause addColumnNumberClause(final ReportGroupColumnNumberClauseContext ctx) {
		ColumnNumberClause result = (ColumnNumberClause) getASGElement(ctx);

		if (result == null) {
			result = new ColumnNumberClauseImpl(programUnit, ctx);

			if (ctx.integerLiteral() != null) {
				final IntegerLiteral integerLiteral = createIntegerLiteral(ctx.integerLiteral());
				result.setIntegerLiteral(integerLiteral);
			}

			columnNumberClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public GroupIndicateClause addGroupIndicateClause(final ReportGroupIndicateClauseContext ctx) {
		GroupIndicateClause result = (GroupIndicateClause) getASGElement(ctx);

		if (result == null) {
			result = new GroupIndicateClauseImpl(programUnit, ctx);

			result.setIndicate(true);

			groupIndicateClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public JustifiedClause addJustifiedClause(final ReportGroupJustifiedClauseContext ctx) {
		JustifiedClause result = (JustifiedClause) getASGElement(ctx);

		if (result == null) {
			result = new JustifiedClauseImpl(programUnit, ctx);

			/*
			 * justified
			 */
			final JustifiedClause.Justified justified;

			if (ctx.RIGHT() != null) {
				justified = JustifiedClause.Justified.JUSTIFIED_RIGHT;
			} else {
				justified = JustifiedClause.Justified.JUSTIFIED;
			}

			result.setJustified(justified);

			justifiedClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PictureClause addPictureClause(final ReportGroupPictureClauseContext ctx) {
		PictureClause result = (PictureClause) getASGElement(ctx);

		if (result == null) {
			result = new PictureClauseImpl(programUnit, ctx);

			final PictureStringContext pictureString = ctx.pictureString();
			result.setPictureString(pictureString.getText());

			pictureClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ResetClause addResetClause(final ReportGroupResetClauseContext ctx) {
		ResetClause result = (ResetClause) getASGElement(ctx);

		if (result == null) {
			result = new ResetClauseImpl(programUnit, ctx);

			final Call dataCall = createCall(ctx.dataName());
			result.setDataCall(dataCall);

			resetClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SignClause addSignClause(final ReportGroupSignClauseContext ctx) {
		SignClause result = (SignClause) getASGElement(ctx);

		if (result == null) {
			result = new SignClauseImpl(programUnit, ctx);

			/*
			 * type
			 */
			final SignClause.SignClauseType type;

			if (ctx.LEADING() != null) {
				type = SignClause.SignClauseType.LEADING;
			} else if (ctx.TRAILING() != null) {
				type = SignClause.SignClauseType.TRAILING;
			} else {
				type = null;
			}

			result.setSignClauseType(type);

			/*
			 * separate
			 */
			if (ctx.SEPARATE() != null) {
				result.setSeparate(true);
			}

			signClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SourceClause addSourceClause(final ReportGroupSourceClauseContext ctx) {
		SourceClause result = (SourceClause) getASGElement(ctx);

		if (result == null) {
			result = new SourceClauseImpl(programUnit, ctx);

			if (ctx.identifier() != null) {
				final Call sourceCall = createCall(ctx.identifier());
				result.setSourceCall(sourceCall);
			}

			sourceClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SumClause addSumClause(final ReportGroupSumClauseContext ctx) {
		SumClause result = (SumClause) getASGElement(ctx);

		if (result == null) {
			result = new SumClauseImpl(programUnit, ctx);

			/*
			 * sum
			 */
			for (final IdentifierContext identifierContext : ctx.identifier()) {
				final Call sumCall = createCall(identifierContext);
				result.addSumCall(sumCall);
			}

			/*
			 * upon
			 */
			for (final DataNameContext dataNameContext : ctx.dataName()) {
				final Call uponCall = createCall(dataNameContext);
				result.addUponCall(uponCall);
			}

			sumClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueClause addValueClause(final ReportGroupValueClauseContext ctx) {
		ValueClause result = (ValueClause) getASGElement(ctx);

		if (result == null) {
			result = new ValueClauseImpl(programUnit, ctx);

			if (ctx.literal() != null) {
				final Literal literal = createLiteral(ctx.literal());
				result.setLiteral(literal);
			}

			valueClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public BlankWhenZeroClause getBlankWhenZeroClause() {
		return blankWhenZeroClause;
	}

	@Override
	public ColumnNumberClause getColumnNumberClause() {
		return columnNumberClause;
	}

	@Override
	public GroupIndicateClause getGroupIndicateClause() {
		return groupIndicateClause;
	}

	@Override
	public JustifiedClause getJustifiedClause() {
		return justifiedClause;
	}

	@Override
	public PictureClause getPictureClause() {
		return pictureClause;
	}

	@Override
	public ReportGroupDescriptionEntryType getReportGroupDescriptionEntryType() {
		return ReportGroupDescriptionEntryType.PRINTABLE;
	}

	@Override
	public ResetClause getResetClause() {
		return resetClause;
	}

	@Override
	public SignClause getSignClause() {
		return signClause;
	}

	@Override
	public SourceClause getSourceClause() {
		return sourceClause;
	}

	@Override
	public SumClause getSumClause() {
		return sumClause;
	}

	@Override
	public ValueClause getValueClause() {
		return valueClause;
	}
}
