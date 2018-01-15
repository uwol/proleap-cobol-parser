/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation.impl;

import io.proleap.cobol.Cobol85Parser.RelationSignConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.type.CobolBaseType;
import io.proleap.cobol.asg.metamodel.type.Type;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.SignCondition;
import io.proleap.cobol.asg.util.CastUtils;

public class SignConditionImpl extends ValueStmtImpl implements SignCondition {

	protected ArithmeticValueStmt arithmeticExpression;

	protected RelationSignConditionContext ctx;

	protected boolean not;

	protected SignConditionType signConditionType;

	public SignConditionImpl(final ProgramUnit programUnit, final RelationSignConditionContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public ArithmeticValueStmt getArithmeticExpression() {
		return arithmeticExpression;
	}

	@Override
	public boolean getNot() {
		return not;
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
		final Boolean result;

		if (CobolBaseType.FLOAT.equals(arithmeticExpression.getType())) {
			final Double value = CastUtils.castDouble(arithmeticExpression.getValue());

			if (SignConditionType.POSITIVE.equals(signConditionType) && not) {
				result = value <= 0;
			} else if (SignConditionType.NEGATIVE.equals(signConditionType) && not) {
				result = value >= 0;
			} else if (SignConditionType.ZERO.equals(signConditionType) && not) {
				result = value != 0;
			} else if (SignConditionType.POSITIVE.equals(signConditionType)) {
				result = value > 0;
			} else if (SignConditionType.NEGATIVE.equals(signConditionType)) {
				result = value < 0;
			} else if (SignConditionType.ZERO.equals(signConditionType)) {
				result = value == 0.0;
			} else {
				result = null;
			}
		} else if (CobolBaseType.INTEGER.equals(arithmeticExpression.getType())) {
			final Long value = CastUtils.castLong(arithmeticExpression.getValue());

			if (SignConditionType.POSITIVE.equals(signConditionType) && not) {
				result = value <= 0;
			} else if (SignConditionType.NEGATIVE.equals(signConditionType) && not) {
				result = value >= 0;
			} else if (SignConditionType.ZERO.equals(signConditionType) && not) {
				result = value != 0;
			} else if (SignConditionType.POSITIVE.equals(signConditionType)) {
				result = value > 0;
			} else if (SignConditionType.NEGATIVE.equals(signConditionType)) {
				result = value < 0;
			} else if (SignConditionType.ZERO.equals(signConditionType)) {
				result = value == 0.0;
			} else {
				result = null;
			}
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public void setArithmeticExpression(final ArithmeticValueStmt arithmeticExpression) {
		this.arithmeticExpression = arithmeticExpression;
	}

	@Override
	public void setNot(final boolean not) {
		this.not = not;
	}

	@Override
	public void setSignConditionType(final SignConditionType signConditionType) {
		this.signConditionType = signConditionType;
	}
}
