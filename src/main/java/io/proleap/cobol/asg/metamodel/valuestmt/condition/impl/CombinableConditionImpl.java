/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition.impl;

import io.proleap.cobol.Cobol85Parser.CombinableConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.CombinableCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class CombinableConditionImpl extends ValueStmtImpl implements CombinableCondition {

	protected CombinableCondition combinableCondition;

	protected CombinableConditionContext ctx;

	protected boolean not;

	public CombinableConditionImpl(final ProgramUnit programUnit, final CombinableConditionContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public CombinableCondition addCombinableCondition(final CombinableConditionContext ctx) {
		CombinableCondition result = (CombinableCondition) getASGElement(ctx);

		if (result == null) {
			result = new CombinableConditionImpl(programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CombinableCondition getCombinableCondition() {
		return combinableCondition;
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
