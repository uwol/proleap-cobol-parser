/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.impl;

import io.proleap.cobol.CobolParser.RelationArithmeticComparisonContext;
import io.proleap.cobol.CobolParser.RelationCombinedComparisonContext;
import io.proleap.cobol.CobolParser.RelationConditionContext;
import io.proleap.cobol.CobolParser.RelationSignConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.RelationConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.ArithmeticComparison;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.CombinedComparison;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.SignCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.impl.ArithmeticComparisonImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.impl.CombinedComparisonImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.impl.SignConditionImpl;

public class RelationConditionValueStmtImpl extends ValueStmtImpl implements RelationConditionValueStmt {

	protected ArithmeticComparison arithmeticComparison;

	protected CombinedComparison combinedComparison;

	protected RelationConditionContext ctx;

	protected RelationConditionValueStmt.RelationConditionType relationConditionType;

	protected SignCondition signCondition;

	public RelationConditionValueStmtImpl(final ProgramUnit programUnit, final RelationConditionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ArithmeticComparison addRelationArithmeticComparison(final RelationArithmeticComparisonContext ctx) {
		ArithmeticComparison result = (ArithmeticComparison) getASGElement(ctx);

		if (result == null) {
			result = new ArithmeticComparisonImpl(programUnit, ctx);

			// arithmetic expression left hand
			final ArithmeticValueStmt arithmeticExpressionLeft = createArithmeticValueStmt(ctx.arithmeticExpression(0));
			result.setArithmeticExpressionLeft(arithmeticExpressionLeft);

			// operator
			result.addOperator(ctx.relationalOperator());

			// arithmetic expression right hand
			final ArithmeticValueStmt arithmeticExpressionRight = createArithmeticValueStmt(
					ctx.arithmeticExpression(1));
			result.setArithmeticExpressionRight(arithmeticExpressionRight);

			arithmeticComparison = result;
			subValueStmts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CombinedComparison addRelationCombinedComparison(final RelationCombinedComparisonContext ctx) {
		CombinedComparison result = (CombinedComparison) getASGElement(ctx);

		if (result == null) {
			result = new CombinedComparisonImpl(programUnit, ctx);

			// arithmetic expression
			final ArithmeticValueStmt arithmeticExpression = createArithmeticValueStmt(ctx.arithmeticExpression());
			result.setArithmeticExpression(arithmeticExpression);

			// operator
			result.addOperator(ctx.relationalOperator());

			// combined condition
			result.addCombinedCondition(ctx.relationCombinedCondition());

			combinedComparison = result;
			subValueStmts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SignCondition addRelationSignCondition(final RelationSignConditionContext ctx) {
		SignCondition result = (SignCondition) getASGElement(ctx);

		if (result == null) {
			result = new SignConditionImpl(programUnit, ctx);

			// type
			final SignCondition.SignConditionType type;

			if (ctx.POSITIVE() != null) {
				type = SignCondition.SignConditionType.POSITIVE;
			} else if (ctx.NEGATIVE() != null) {
				type = SignCondition.SignConditionType.NEGATIVE;
			} else if (ctx.ZERO() != null) {
				type = SignCondition.SignConditionType.ZERO;
			} else {
				type = null;
			}

			result.setSignConditionType(type);

			// not
			if (ctx.NOT() != null) {
				result.setNot(true);
			}

			// arithmetic
			final ArithmeticValueStmt arithmeticExpression = createArithmeticValueStmt(ctx.arithmeticExpression());
			result.setArithmeticExpression(arithmeticExpression);

			signCondition = result;
			subValueStmts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ArithmeticComparison getArithmeticComparison() {
		return arithmeticComparison;
	}

	@Override
	public CombinedComparison getCombinedComparison() {
		return combinedComparison;
	}

	@Override
	public RelationConditionType getRelationConditionType() {
		return relationConditionType;
	}

	@Override
	public SignCondition getSignCondition() {
		return signCondition;
	}

	@Override
	public void setRelationConditionType(final RelationConditionType relationConditionType) {
		this.relationConditionType = relationConditionType;
	}
}
