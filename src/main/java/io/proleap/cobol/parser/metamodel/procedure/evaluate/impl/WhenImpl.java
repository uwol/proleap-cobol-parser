/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.evaluate.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.EvaluateAlsoConditionContext;
import io.proleap.cobol.Cobol85Parser.EvaluateConditionContext;
import io.proleap.cobol.Cobol85Parser.EvaluateValueContext;
import io.proleap.cobol.Cobol85Parser.EvaluateWhenContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.AlsoCondition;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.Condition;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.When;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class WhenImpl extends CobolDivisionElementImpl implements When {

	protected List<AlsoCondition> alsoConditions = new ArrayList<AlsoCondition>();

	protected Condition condition;

	protected final EvaluateWhenContext ctx;

	public WhenImpl(final ProgramUnit programUnit, final EvaluateWhenContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AlsoCondition addAlsoCondition(final EvaluateAlsoConditionContext ctx) {
		AlsoCondition result = (AlsoCondition) getASGElement(ctx);

		if (result == null) {
			result = new AlsoConditionImpl(programUnit, ctx);

			// condition
			result.addCondition(ctx.evaluateCondition());

			alsoConditions.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Condition addCondition(final EvaluateConditionContext ctx) {
		Condition result = (Condition) getASGElement(ctx);

		if (result == null) {
			result = new ConditionImpl(programUnit, ctx);

			// type and condition
			final Condition.Type type;
			final ValueStmt conditionValueStmt;

			if (ctx.ANY() != null) {
				type = Condition.Type.Any;
				conditionValueStmt = null;
			} else if (ctx.condition() != null) {
				type = Condition.Type.Condition;
				conditionValueStmt = createConditionValueStmt(ctx.condition());
			} else if (ctx.booleanLiteral() != null) {
				type = Condition.Type.Boolean;
				conditionValueStmt = createBooleanLiteralValueStmt(ctx.booleanLiteral());
			} else if (ctx.evaluateThrough() != null) {
				type = Condition.Type.ValueThrough;
				conditionValueStmt = null;
			} else if (ctx.evaluateValue() != null) {
				type = Condition.Type.Value;
				conditionValueStmt = null;
			} else {
				type = null;
				conditionValueStmt = null;
			}

			result.setType(type);
			result.setConditionValueStmt(conditionValueStmt);

			// not
			if (ctx.NOT() != null) {
				result.setNot(true);
			}

			// value
			if (ctx.evaluateValue() != null) {
				result.addValue(ctx.evaluateValue());
			}

			// through
			if (ctx.evaluateThrough() != null) {
				result.addThrough(ctx.evaluateThrough());
			}

			condition = result;
			registerASGElement(result);
		}

		return result;
	}

	protected ValueStmt createValueStmt(final EvaluateValueContext ctx) {
		final ValueStmt result = createValueStmt(ctx.identifier(), ctx.literal(), ctx.arithmeticExpression());
		return result;
	}

	@Override
	public List<AlsoCondition> getAlsoConditions() {
		return alsoConditions;
	}

	@Override
	public Condition getCondition() {
		return condition;
	}

}
