/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation.impl;

import io.proleap.cobol.CobolParser.ArithmeticExpressionContext;
import io.proleap.cobol.CobolParser.RelationCombinedComparisonContext;
import io.proleap.cobol.CobolParser.RelationCombinedConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.CombinedComparison;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.CombinedCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.CombinedCondition.CombinedConditionType;

public class CombinedComparisonImpl extends AbstractComparisonValueStmtImpl implements CombinedComparison {

	protected ArithmeticValueStmt arithmeticExpression;

	protected CombinedCondition combinedCondition;

	protected RelationCombinedComparisonContext ctx;

	public CombinedComparisonImpl(final ProgramUnit programUnit, final RelationCombinedComparisonContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public CombinedCondition addCombinedCondition(final RelationCombinedConditionContext ctx) {
		CombinedCondition result = (CombinedCondition) getASGElement(ctx);

		if (result == null) {
			result = new CombinedConditionImpl(programUnit, ctx);

			for (final ArithmeticExpressionContext arithmeticExpressionContext : ctx.arithmeticExpression()) {
				final ArithmeticValueStmt arithmeticExpression = createArithmeticValueStmt(arithmeticExpressionContext);
				result.addArithmeticExpression(arithmeticExpression);

				// type
				final CombinedConditionType combinedConditionType;

				if (!ctx.AND().isEmpty()) {
					combinedConditionType = CombinedConditionType.AND;
				} else if (!ctx.OR().isEmpty()) {
					combinedConditionType = CombinedConditionType.OR;
				} else {
					combinedConditionType = null;
				}

				result.setCombinedConditionType(combinedConditionType);
			}

			combinedCondition = result;
			subValueStmts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ArithmeticValueStmt getArithmeticExpression() {
		return arithmeticExpression;
	}

	@Override
	public CombinedCondition getCombinedCondition() {
		return combinedCondition;
	}

	@Override
	public void setArithmeticExpression(final ArithmeticValueStmt arithmeticExpression) {
		this.arithmeticExpression = arithmeticExpression;
	}
}
