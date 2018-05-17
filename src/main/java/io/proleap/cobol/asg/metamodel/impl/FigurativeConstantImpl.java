/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import io.proleap.cobol.CobolParser.FigurativeConstantContext;
import io.proleap.cobol.asg.metamodel.FigurativeConstant;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.ProgramUnit;

public class FigurativeConstantImpl extends CobolDivisionElementImpl implements FigurativeConstant {

	protected final FigurativeConstantContext ctx;

	protected final FigurativeConstantType figurativeConstantType;

	protected Literal literal;

	public FigurativeConstantImpl(final FigurativeConstantType figurativeConstantType, final ProgramUnit programUnit,
			final FigurativeConstantContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.figurativeConstantType = figurativeConstantType;
	}

	@Override
	public FigurativeConstantContext getCtx() {
		return ctx;
	}

	@Override
	public FigurativeConstantType getFigurativeConstantType() {
		return figurativeConstantType;
	}

	@Override
	public Literal getLiteral() {
		return literal;
	}

	@Override
	public void setLiteral(final Literal literal) {
		this.literal = literal;
	}

	@Override
	public String toString() {
		return super.toString() + ", figurativeConstantType=[" + figurativeConstantType + "]";
	}
}
