/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation;

import io.proleap.cobol.CobolParser.AbbreviationContext;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;

public interface Abbreviation extends ComparisonStmt {

	Abbreviation addAbbreviation(AbbreviationContext ctx);

	Abbreviation getAbbreviation();

	ArithmeticValueStmt getArithmeticExpression();

	void setArithmeticExpression(ArithmeticValueStmt arithmeticExpression);

}
