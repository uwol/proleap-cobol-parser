/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.RelationCombinedConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.CombinedCondition;

public class CombinedConditionImpl extends ValueStmtImpl implements CombinedCondition {

	protected List<ArithmeticValueStmt> arithmeticExpressions = new ArrayList<ArithmeticValueStmt>();

	protected CombinedConditionType combinedConditionType;

	protected RelationCombinedConditionContext ctx;

	public CombinedConditionImpl(final ProgramUnit programUnit, final RelationCombinedConditionContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public void addArithmeticExpression(final ArithmeticValueStmt arithmeticExpression) {
		arithmeticExpressions.add(arithmeticExpression);
	}

	@Override
	public List<ArithmeticValueStmt> getArithmeticExpressions() {
		return arithmeticExpressions;
	}

	@Override
	public CombinedConditionType getCombinedConditionType() {
		return combinedConditionType;
	}

	@Override
	public void setCombinedConditionType(final CombinedConditionType combinedConditionType) {
		this.combinedConditionType = combinedConditionType;
	}
}
