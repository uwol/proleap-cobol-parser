/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel;

import io.proleap.cobol.Cobol85Parser.LiteralContext;

public interface Literal extends CobolDivisionElement {

	public enum Type {
		BOOLEAN, CICS_DFH_RESP, CICS_DFH_VALUE, FIGURATIVE_CONSTANT, NON_NUMERIC, NUMERIC
	}

	BooleanLiteral getBooleanLiteral();

	@Override
	LiteralContext getCtx();

	FigurativeConstant getFigurativeConstant();

	String getNonNumericLiteral();

	NumericLiteral getNumericLiteral();

	Type getType();

	void setBooleanLiteral(BooleanLiteral booleanLiteral);

	void setFigurativeConstant(FigurativeConstant figurativeConstant);

	void setNonNumericLiteral(String nonNumericLiteral);

	void setNumericLiteral(NumericLiteral numericLiteral);

	void setType(Type type);
}
