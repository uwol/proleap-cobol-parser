/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.AbbreviationContext;
import io.proleap.cobol.CobolParser.AndOrConditionContext;
import io.proleap.cobol.CobolParser.CombinableConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.AndOrCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.CombinableCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.Abbreviation;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.impl.AbbreviationImpl;

public class AndOrConditionImpl extends ValueStmtImpl implements AndOrCondition {

	protected List<Abbreviation> abbreviations = new ArrayList<Abbreviation>();

	protected AndOrConditionType andOrConditionType;

	protected CombinableCondition combinableCondition;

	protected AndOrConditionContext ctx;

	public AndOrConditionImpl(final ProgramUnit programUnit, final AndOrConditionContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public Abbreviation addAbbreviation(final AbbreviationContext ctx) {
		Abbreviation result = (Abbreviation) getASGElement(ctx);

		if (result == null) {
			result = new AbbreviationImpl(programUnit, ctx);

			// operator
			if (ctx.relationalOperator() != null) {
				result.addOperator(ctx.relationalOperator());
			}

			// arithmetic expression
			if (ctx.arithmeticExpression() != null) {
				final ArithmeticValueStmt arithmeticExpression = createArithmeticValueStmt(ctx.arithmeticExpression());
				result.setArithmeticExpression(arithmeticExpression);
			}

			// abbreviation
			if (ctx.abbreviation() != null) {
				result.addAbbreviation(ctx.abbreviation());
			}

			abbreviations.add(result);
			subValueStmts.add(result);
			registerASGElement(result);
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
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Abbreviation> getAbbreviations() {
		return abbreviations;
	}

	@Override
	public AndOrConditionType getAndOrConditionType() {
		return andOrConditionType;
	}

	@Override
	public CombinableCondition getCombinableCondition() {
		return combinableCondition;
	}

	@Override
	public void setAndOrConditionType(final AndOrConditionType andOrConditionType) {
		this.andOrConditionType = andOrConditionType;
	}
}
