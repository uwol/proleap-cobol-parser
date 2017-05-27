/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.RelationCombinedConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.type.CobolBaseType;
import io.proleap.cobol.asg.metamodel.type.Type;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.CombinedCondition;

public class CombinedConditionImpl extends ValueStmtImpl implements CombinedCondition {

	protected List<ArithmeticValueStmt> arithmeticExpressions = new ArrayList<ArithmeticValueStmt>();

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
	public Type getType() {
		return CobolBaseType.BOOLEAN;
	}

	@Override
	public Object getValue() {
		return null;
	}

}
