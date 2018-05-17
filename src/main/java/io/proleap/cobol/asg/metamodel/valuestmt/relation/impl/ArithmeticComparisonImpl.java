/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation.impl;

import io.proleap.cobol.CobolParser.RelationArithmeticComparisonContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.ArithmeticComparison;

public class ArithmeticComparisonImpl extends AbstractComparisonValueStmtImpl implements ArithmeticComparison {

	protected ArithmeticValueStmt arithmeticExpressionLeft;

	protected ArithmeticValueStmt arithmeticExpressionRight;

	protected RelationArithmeticComparisonContext ctx;

	public ArithmeticComparisonImpl(final ProgramUnit programUnit, final RelationArithmeticComparisonContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public ArithmeticValueStmt getArithmeticExpressionLeft() {
		return arithmeticExpressionLeft;
	}

	@Override
	public ArithmeticValueStmt getArithmeticExpressionRight() {
		return arithmeticExpressionRight;
	}

	@Override
	public void setArithmeticExpressionLeft(final ArithmeticValueStmt arithmeticExpressionLeft) {
		this.arithmeticExpressionLeft = arithmeticExpressionLeft;
	}

	@Override
	public void setArithmeticExpressionRight(final ArithmeticValueStmt arithmeticExpressionRight) {
		this.arithmeticExpressionRight = arithmeticExpressionRight;
	}
}
