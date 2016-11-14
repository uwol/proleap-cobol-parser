/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.specialnames.impl;

import io.proleap.cobol.Cobol85Parser.ChannelClauseContext;
import io.proleap.cobol.Cobol85Parser.ClassClauseContext;
import io.proleap.cobol.Cobol85Parser.ClassClauseThroughContext;
import io.proleap.cobol.Cobol85Parser.CurrencySignClauseContext;
import io.proleap.cobol.Cobol85Parser.OdtClauseContext;
import io.proleap.cobol.Cobol85Parser.SpecialNamesParagraphContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.Literal;
import io.proleap.cobol.parser.metamodel.MnemonicName;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.specialnames.ChannelClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.ClassClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.CurrencySignClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.OdtClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.SpecialNamesParagraph;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class SpecialNamesParagraphImpl extends CobolDivisionElementImpl implements SpecialNamesParagraph {

	protected ChannelClause channelClause;

	protected ClassClause classClause;

	protected final SpecialNamesParagraphContext ctx;

	protected CurrencySignClause currencySignClause;

	protected OdtClause odtClause;

	public SpecialNamesParagraphImpl(final ProgramUnit programUnit, final SpecialNamesParagraphContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ChannelClause addChannelClause(final ChannelClauseContext ctx) {
		ChannelClause result = (ChannelClause) getASGElement(ctx);

		if (result == null) {
			result = new ChannelClauseImpl(programUnit, ctx);

			final IntegerLiteral integerLiteral = addIntegerLiteral(ctx.integerLiteral());
			result.setIntegerLiteral(integerLiteral);

			final MnemonicName mnemonicName = addMnemonicName(ctx.mnemonicName());
			result.setMnemonicName(mnemonicName);

			channelClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ClassClause addClassClause(final ClassClauseContext ctx) {
		ClassClause result = (ClassClause) getASGElement(ctx);

		if (result == null) {
			result = new ClassClauseImpl(programUnit, ctx);

			/*
			 * class name
			 */
			final ValueStmt classNameValueStmt = createCallValueStmt(ctx.className());
			result.setClassNameValueStmt(classNameValueStmt);

			/*
			 * type
			 */
			final ClassClause.Type type;

			if (ctx.ALPHANUMERIC() != null) {
				type = ClassClause.Type.AlphaNumeric;
			} else if (ctx.NATIONAL() != null) {
				type = ClassClause.Type.National;
			} else {
				type = null;
			}

			result.setType(type);

			/*
			 * class through
			 */
			for (final ClassClauseThroughContext classClauseThroughContext : ctx.classClauseThrough()) {
				result.addClassClauseThrough(classClauseThroughContext);
			}

			classClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CurrencySignClause addCurrencySignClause(final CurrencySignClauseContext ctx) {
		CurrencySignClause result = (CurrencySignClause) getASGElement(ctx);

		if (result == null) {
			result = new CurrencySignClauseImpl(programUnit, ctx);

			final Literal currencyLiteral = addLiteral(ctx.literal(0));
			result.setCurrencyLiteral(currencyLiteral);

			if (ctx.literal().size() > 1) {
				final Literal pictureSymbolLiteral = addLiteral(ctx.literal(1));
				result.setPictureSymbolLiteral(pictureSymbolLiteral);
			}

			currencySignClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OdtClause addOdtClause(final OdtClauseContext ctx) {
		OdtClause result = (OdtClause) getASGElement(ctx);

		if (result == null) {
			result = new OdtClauseImpl(programUnit, ctx);

			final MnemonicName mnemonicName = addMnemonicName(ctx.mnemonicName());
			result.setMnemonicName(mnemonicName);

			odtClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ChannelClause getChannelClause() {
		return channelClause;
	}

	@Override
	public ClassClause getClassClause() {
		return classClause;
	}

	@Override
	public CurrencySignClause getCurrencySignClause() {
		return currencySignClause;
	}

	@Override
	public OdtClause getOdtClause() {
		return odtClause;
	}

}
