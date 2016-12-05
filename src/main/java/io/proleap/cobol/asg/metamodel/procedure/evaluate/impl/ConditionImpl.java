/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.evaluate.impl;

import io.proleap.cobol.Cobol85Parser.EvaluateConditionContext;
import io.proleap.cobol.Cobol85Parser.EvaluateThroughContext;
import io.proleap.cobol.Cobol85Parser.EvaluateValueContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.Condition;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.Through;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.Value;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ConditionImpl extends CobolDivisionElementImpl implements Condition {

	protected ValueStmt conditionValueStmt;

	protected final EvaluateConditionContext ctx;

	protected boolean not;

	protected Through through;

	protected Type type;

	protected Value value;

	public ConditionImpl(final ProgramUnit programUnit, final EvaluateConditionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Through addThrough(final EvaluateThroughContext ctx) {
		Through result = (Through) getASGElement(ctx);

		if (result == null) {
			result = new ThroughImpl(programUnit, ctx);

			result.addValue(ctx.evaluateValue());

			through = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Value addValue(final EvaluateValueContext ctx) {
		Value result = (Value) getASGElement(ctx);

		if (result == null) {
			result = new ValueImpl(programUnit, ctx);

			final ValueStmt valueStmt = createValueStmt(ctx.identifier(), ctx.literal(), ctx.arithmeticExpression());
			result.setValueStmt(valueStmt);

			value = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt getConditionValueStmt() {
		return conditionValueStmt;
	}

	@Override
	public Through getThrough() {
		return through;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Value getValue() {
		return value;
	}

	@Override
	public boolean isNot() {
		return not;
	}

	@Override
	public void setConditionValueStmt(final ValueStmt conditionValueStmt) {
		this.conditionValueStmt = conditionValueStmt;
	}

	@Override
	public void setNot(final boolean not) {
		this.not = not;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
