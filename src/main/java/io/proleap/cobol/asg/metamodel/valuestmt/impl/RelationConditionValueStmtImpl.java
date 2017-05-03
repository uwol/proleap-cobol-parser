/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.impl;

import io.proleap.cobol.Cobol85Parser.RelationArithmeticComparisonContext;
import io.proleap.cobol.Cobol85Parser.RelationCombinedComparisonContext;
import io.proleap.cobol.Cobol85Parser.RelationConditionContext;
import io.proleap.cobol.Cobol85Parser.RelationSignConditionContext;
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

	protected SignCondition signCondition;

	protected RelationConditionValueStmt.Type type;

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
			final SignCondition.Type type;

			if (ctx.POSITIVE() != null) {
				type = SignCondition.Type.POSITIVE;
			} else if (ctx.NEGATIVE() != null) {
				type = SignCondition.Type.NEGATIVE;
			} else if (ctx.ZERO() != null) {
				type = SignCondition.Type.ZERO;
			} else {
				type = null;
			}

			result.setType(type);

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
	public SignCondition getSignCondition() {
		return signCondition;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
