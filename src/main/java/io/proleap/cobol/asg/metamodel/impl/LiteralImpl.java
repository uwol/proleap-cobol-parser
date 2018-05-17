/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import io.proleap.cobol.CobolParser.LiteralContext;
import io.proleap.cobol.asg.metamodel.BooleanLiteral;
import io.proleap.cobol.asg.metamodel.FigurativeConstant;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.NumericLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;

public class LiteralImpl extends CobolDivisionElementImpl implements Literal {

	protected BooleanLiteral booleanLiteral;

	protected final LiteralContext ctx;

	protected FigurativeConstant figurativeConstant;

	protected LiteralType literalType;

	protected String nonNumericLiteral;

	protected NumericLiteral numericLiteral;

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
	public LiteralType getLiteralType() {
		return literalType;
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
	public Object getValue() {
		final Object result;

		switch (literalType) {
		case NON_NUMERIC:
			result = nonNumericLiteral;
			break;
		case NUMERIC:
			result = numericLiteral.getValue();
			break;
		case BOOLEAN:
			result = booleanLiteral.getValue();
			break;
		case FIGURATIVE_CONSTANT:
			result = figurativeConstant;
			break;
		default:
			result = null;
		}

		return result;
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
	public void setLiteralType(final LiteralType type) {
		literalType = type;
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
	public String toString() {
		return super.toString() + ", literalType=[" + literalType + "]";
	}
}
