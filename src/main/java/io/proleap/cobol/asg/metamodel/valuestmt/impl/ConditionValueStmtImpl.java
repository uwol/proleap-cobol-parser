/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.AndOrConditionContext;
import io.proleap.cobol.Cobol85Parser.CombinableConditionContext;
import io.proleap.cobol.Cobol85Parser.ConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.AndOrCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.CombinableCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.impl.AndOrConditionImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.impl.CombinableConditionImpl;

public class ConditionValueStmtImpl extends ValueStmtImpl implements ConditionValueStmt {

	protected List<AndOrCondition> andOrConditions = new ArrayList<AndOrCondition>();

	protected CombinableCondition combinableCondition;

	protected ConditionContext ctx;

	public ConditionValueStmtImpl(final ProgramUnit programUnit, final ConditionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AndOrCondition addAndOrCondition(final AndOrConditionContext ctx) {
		AndOrCondition result = (AndOrCondition) getASGElement(ctx);

		if (result == null) {
			result = new AndOrConditionImpl(programUnit, ctx);

			// type
			final AndOrCondition.Type type;

			if (ctx.AND() != null) {
				type = AndOrCondition.Type.And;
			} else if (ctx.OR() != null) {
				type = AndOrCondition.Type.Or;
			} else {
				type = null;
			}

			result.setType(type);

			// combinable condition
			if (ctx.combinableCondition() != null) {
				result.addCombinableCondition(ctx.combinableCondition());
			}

			// abbreviation rest
			if (ctx.abbreviationRest() != null) {
				result.addAbbreviationRest(ctx.abbreviationRest());
			}

			andOrConditions.add(result);
			subValueStmts.add(result);
		}

		return result;
	}

	@Override
	public CombinableCondition addCombinableCondition(final CombinableConditionContext ctx) {
		CombinableCondition result = (CombinableCondition) getASGElement(ctx);

		if (result == null) {
			result = new CombinableConditionImpl(programUnit, ctx);

			// not
			final boolean not = ctx.NOT() != null;
			result.setNot(not);

			// simple condition
			result.addSimpleCondition(ctx.simpleCondition());

			combinableCondition = result;
			subValueStmts.add(result);
		}

		return result;
	}

	@Override
	public List<AndOrCondition> getAndOrConditions() {
		return andOrConditions;
	}

	@Override
	public CombinableCondition getCombinableCondition() {
		return combinableCondition;
	}

	@Override
	public String getValue() {
		return null;
	}

}
