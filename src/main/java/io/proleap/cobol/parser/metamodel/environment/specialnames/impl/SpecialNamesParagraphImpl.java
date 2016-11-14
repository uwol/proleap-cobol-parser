/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.specialnames.impl;

import io.proleap.cobol.Cobol85Parser.ChannelClauseContext;
import io.proleap.cobol.Cobol85Parser.OdtClauseContext;
import io.proleap.cobol.Cobol85Parser.SpecialNamesParagraphContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.MnemonicName;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.specialnames.ChannelClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.OdtClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.SpecialNamesParagraph;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class SpecialNamesParagraphImpl extends CobolDivisionElementImpl implements SpecialNamesParagraph {

	protected ChannelClause channelClause;

	protected final SpecialNamesParagraphContext ctx;

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
	public OdtClause getOdtClause() {
		return odtClause;
	}

}
