/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.search.impl;

import io.proleap.cobol.Cobol85Parser.SearchWhenContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.search.WhenPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;

public class WhenImpl extends ScopeImpl implements WhenPhrase {

	protected ConditionValueStmt condition;

	protected final SearchWhenContext ctx;

	protected WhenType whenType;

	public WhenImpl(final ProgramUnit programUnit, final SearchWhenContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ConditionValueStmt getCondition() {
		return condition;
	}

	@Override
	public WhenType getWhenType() {
		return whenType;
	}

	@Override
	public void setCondition(final ConditionValueStmt condition) {
		this.condition = condition;
	}

	@Override
	public void setWhenType(final WhenType whenType) {
		this.whenType = whenType;
	}

}
