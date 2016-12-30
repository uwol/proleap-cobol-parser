/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.AbbreviationContext;
import io.proleap.cobol.Cobol85Parser.AndOrConditionContext;
import io.proleap.cobol.Cobol85Parser.CombinableConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.AndOrCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.CombinableCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.Abbreviation;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.impl.AbbreviationImpl;

public class AndOrConditionImpl extends ValueStmtImpl implements AndOrCondition {

	protected List<Abbreviation> abbreviations = new ArrayList<Abbreviation>();

	protected CombinableCondition combinableCondition;

	protected AndOrConditionContext ctx;

	protected Type type;

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
	public CombinableCondition getCombinableCondition() {
		return combinableCondition;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
