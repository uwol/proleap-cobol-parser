/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames.impl;

import io.proleap.cobol.CobolParser.CurrencySignClauseContext;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.specialnames.CurrencySignClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

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
