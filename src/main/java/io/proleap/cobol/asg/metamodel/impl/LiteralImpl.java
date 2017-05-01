/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.asg.metamodel.BooleanLiteral;
import io.proleap.cobol.asg.metamodel.FigurativeConstant;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.NumericLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;

public class LiteralImpl extends CobolDivisionElementImpl implements Literal {

	protected BooleanLiteral booleanLiteral;

	protected final LiteralContext ctx;

	protected FigurativeConstant figurativeConstant;

	protected String nonNumericLiteral;

	protected NumericLiteral numericLiteral;

	protected Type type;

	public LiteralImpl(final ProgramUnit programUnit, final LiteralContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public BooleanLiteral getBooleanLiteral() {
		return booleanLiteral;
	}

	@Override
	public LiteralContext getCtx() {
		return ctx;
	}

	@Override
	public FigurativeConstant getFigurativeConstant() {
		return figurativeConstant;
	}

	@Override
	public String getNonNumericLiteral() {
		return nonNumericLiteral;
	}

	@Override
	public NumericLiteral getNumericLiteral() {
		return numericLiteral;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setBooleanLiteral(final BooleanLiteral booleanLiteral) {
		this.booleanLiteral = booleanLiteral;
	}

	@Override
	public void setFigurativeConstant(final FigurativeConstant figurativeConstant) {
		this.figurativeConstant = figurativeConstant;
	}

	@Override
	public void setNonNumericLiteral(final String nonNumericLiteral) {
		this.nonNumericLiteral = nonNumericLiteral;
	}

	@Override
	public void setNumericLiteral(final NumericLiteral numericLiteral) {
		this.numericLiteral = numericLiteral;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return super.toString() + ", type=[" + type + "]";
	}
}
