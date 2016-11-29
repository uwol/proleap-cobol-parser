/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.search.impl;

import io.proleap.cobol.Cobol85Parser.SearchWhenContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.ScopeImpl;
import io.proleap.cobol.parser.metamodel.procedure.search.When;
import io.proleap.cobol.parser.metamodel.valuestmt.ConditionValueStmt;

public class WhenImpl extends ScopeImpl implements When {

	protected ConditionValueStmt condition;

	protected final SearchWhenContext ctx;

	protected Type type;

	public WhenImpl(final ProgramUnit programUnit, final SearchWhenContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ConditionValueStmt getCondition() {
		return condition;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setCondition(final ConditionValueStmt condition) {
		this.condition = condition;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
