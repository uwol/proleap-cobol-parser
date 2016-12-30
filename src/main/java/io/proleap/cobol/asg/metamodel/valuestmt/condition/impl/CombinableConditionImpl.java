/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition.impl;

import io.proleap.cobol.Cobol85Parser.CombinableConditionContext;
import io.proleap.cobol.Cobol85Parser.SimpleConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.CombinableCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.SimpleCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class CombinableConditionImpl extends ValueStmtImpl implements CombinableCondition {

	protected CombinableConditionContext ctx;

	protected boolean not;

	protected SimpleCondition simpleCondition;

	public CombinableConditionImpl(final ProgramUnit programUnit, final CombinableConditionContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public SimpleCondition addSimpleCondition(final SimpleConditionContext ctx) {
		SimpleCondition result = (SimpleCondition) getASGElement(ctx);

		if (result == null) {
			result = new SimpleConditionImpl(programUnit, ctx);

			// type
			final SimpleCondition.Type type;

			if (ctx.condition() != null) {
				result.addCondition(ctx.condition());
				type = SimpleCondition.Type.CONDITION;
			} else if (ctx.relationCondition() != null) {
				result.addRelationCondition(ctx.relationCondition());
				type = SimpleCondition.Type.RELATION_CONDITION;
			} else if (ctx.classCondition() != null) {
				result.addClassCondition(ctx.classCondition());
				type = SimpleCondition.Type.CLASS_CONDITION;
			} else if (ctx.conditionNameReference() != null) {
				result.addConditionNameReference(ctx.conditionNameReference());
				type = SimpleCondition.Type.CONDITION_NAME_REFERENCE;
			} else {
				type = null;
			}

			result.setType(type);

			simpleCondition = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SimpleCondition getSimpleCondition() {
		return simpleCondition;
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public boolean isNot() {
		return not;
	}

	@Override
	public void setNot(final boolean not) {
		this.not = not;
	}

}
