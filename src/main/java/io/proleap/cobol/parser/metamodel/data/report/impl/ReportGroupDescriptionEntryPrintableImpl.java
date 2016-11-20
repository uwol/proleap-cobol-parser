/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.DataNameContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.PictureStringContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupBlankWhenZeroClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupColumnNumberClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupDescriptionEntryFormat3Context;
import io.proleap.cobol.Cobol85Parser.ReportGroupIndicateClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupJustifiedClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupPictureClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupResetClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupSignClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupSourceClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupSumClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportGroupValueClauseContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.Literal;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.BlankWhenZeroClause;
import io.proleap.cobol.parser.metamodel.data.report.ColumnNumberClause;
import io.proleap.cobol.parser.metamodel.data.report.GroupIndicateClause;
import io.proleap.cobol.parser.metamodel.data.report.JustifiedClause;
import io.proleap.cobol.parser.metamodel.data.report.PictureClause;
import io.proleap.cobol.parser.metamodel.data.report.ReportGroupDescriptionEntryPrintable;
import io.proleap.cobol.parser.metamodel.data.report.ResetClause;
import io.proleap.cobol.parser.metamodel.data.report.SignClause;
import io.proleap.cobol.parser.metamodel.data.report.SourceClause;
import io.proleap.cobol.parser.metamodel.data.report.SumClause;
import io.proleap.cobol.parser.metamodel.data.report.ValueClause;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

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

			final IntegerLiteral integerLiteral = addIntegerLiteral(ctx.integerLiteral());
			result.setIntegerLiteral(integerLiteral);

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
				justified = JustifiedClause.Justified.JustifiedRight;
			} else {
				justified = JustifiedClause.Justified.Justified;
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

			final ValueStmt dataValueStmt = createCallValueStmt(ctx.dataName());
			result.setDataValueStmt(dataValueStmt);

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
			final SignClause.Type type;

			if (ctx.LEADING() != null) {
				type = SignClause.Type.Leading;
			} else if (ctx.TRAILING() != null) {
				type = SignClause.Type.Trailing;
			} else {
				type = null;
			}

			result.setType(type);

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

			final ValueStmt sourceValueStmt = createCallValueStmt(ctx.identifier());
			result.setSourceValueStmt(sourceValueStmt);

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
				final ValueStmt sumValueStmt = createCallValueStmt(identifierContext);
				result.addSumValueStmt(sumValueStmt);
			}

			/*
			 * upon
			 */
			for (final DataNameContext dataNameContext : ctx.dataName()) {
				final ValueStmt uponValueStmt = createCallValueStmt(dataNameContext);
				result.addUponValueStmt(uponValueStmt);
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

			final Literal literal = addLiteral(ctx.literal());
			result.setLiteral(literal);

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
	public Type getType() {
		return Type.Printable;
	}

	@Override
	public ValueClause getValueClause() {
		return valueClause;
	}
}
