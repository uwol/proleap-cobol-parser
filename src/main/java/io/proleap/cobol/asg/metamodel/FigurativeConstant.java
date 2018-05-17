/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel;

import io.proleap.cobol.CobolParser.FigurativeConstantContext;

public interface FigurativeConstant extends CobolDivisionElement {

	public enum FigurativeConstantType {
		ALL, HIGH_VALUE, HIGH_VALUES, LOW_VALUE, LOW_VALUES, NULL, NULLS, QUOTE, QUOTES, SPACE, SPACES, ZERO, ZEROES, ZEROS
	}

	@Override
	FigurativeConstantContext getCtx();

	FigurativeConstantType getFigurativeConstantType();

	Literal getLiteral();

	void setLiteral(Literal literal);
}
