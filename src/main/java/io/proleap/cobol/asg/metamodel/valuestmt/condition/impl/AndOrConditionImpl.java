/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition.impl;

import io.proleap.cobol.Cobol85Parser.AndOrConditionContext;
import io.proleap.cobol.Cobol85Parser.CombinableConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.AndOrCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.CombinableCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class AndOrConditionImpl extends ValueStmtImpl implements AndOrCondition {

	protected CombinableCondition combinableCondition;

	protected AndOrConditionContext ctx;

	protected Type type;

	public AndOrConditionImpl(final ProgramUnit programUnit, final AndOrConditionContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public CombinableCondition addCombinableCondition(final CombinableConditionContext ctx) {
		// TODO
		return null;
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
