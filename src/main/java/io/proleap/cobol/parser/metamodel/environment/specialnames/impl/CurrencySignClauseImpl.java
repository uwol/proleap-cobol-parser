/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.specialnames.impl;

import io.proleap.cobol.Cobol85Parser.CurrencySignClauseContext;
import io.proleap.cobol.parser.metamodel.Literal;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.specialnames.CurrencySignClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class CurrencySignClauseImpl extends CobolDivisionElementImpl implements CurrencySignClause {

	protected final CurrencySignClauseContext ctx;

	protected Literal currencyLiteral;

	protected Literal pictureSymbolLiteral;

	public CurrencySignClauseImpl(final ProgramUnit programUnit, final CurrencySignClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Literal getCurrencyLiteral() {
		return currencyLiteral;
	}

	@Override
	public Literal getPictureSymbolLiteral() {
		return pictureSymbolLiteral;
	}

	@Override
	public void setCurrencyLiteral(final Literal currencyLiteral) {
		this.currencyLiteral = currencyLiteral;
	}

	@Override
	public void setPictureSymbolLiteral(final Literal pictureSymbolLiteral) {
		this.pictureSymbolLiteral = pictureSymbolLiteral;
	}

}
