/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation.impl;

import io.proleap.cobol.Cobol85Parser.RelationSignConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.type.CobolBaseType;
import io.proleap.cobol.asg.metamodel.type.Type;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.SignCondition;

public class SignConditionImpl extends ValueStmtImpl implements SignCondition {

	protected ArithmeticValueStmt arithmeticExpression;

	protected RelationSignConditionContext ctx;

	protected SignConditionType signConditionType;

	public SignConditionImpl(final ProgramUnit programUnit, final RelationSignConditionContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public ArithmeticValueStmt getArithmeticExpression() {
		return arithmeticExpression;
	}

	@Override
	public SignConditionType getSignConditionType() {
		return signConditionType;
	}

	@Override
	public Type getType() {
		return CobolBaseType.BOOLEAN;
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public void setArithmeticExpression(final ArithmeticValueStmt arithmeticExpression) {
		this.arithmeticExpression = arithmeticExpression;
	}

	@Override
	public void setSignConditionType(final SignConditionType signConditionType) {
		this.signConditionType = signConditionType;
	}

}
