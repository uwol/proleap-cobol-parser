/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import io.proleap.cobol.CobolParser.PerformTestClauseContext;
import io.proleap.cobol.CobolParser.PerformUntilContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.TestClause;
import io.proleap.cobol.asg.metamodel.procedure.perform.Until;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;

public class UntilImpl extends CobolDivisionElementImpl implements Until {

	protected ConditionValueStmt condition;

	protected final PerformUntilContext ctx;

	protected TestClause testClause;

	public UntilImpl(final ProgramUnit programUnit, final PerformUntilContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public TestClause addTestClause(final PerformTestClauseContext ctx) {
		TestClause result = (TestClause) getASGElement(ctx);

		if (result == null) {
			result = new TestClauseImpl(programUnit, ctx);

			final TestClause.TestClauseType type;

			if (ctx.BEFORE() != null) {
				type = TestClause.TestClauseType.BEFORE;
			} else if (ctx.AFTER() != null) {
				type = TestClause.TestClauseType.AFTER;
			} else {
				type = null;
			}

			result.setTestClauseType(type);

			testClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ConditionValueStmt getCondition() {
		return condition;
	}

	@Override
	public TestClause getTestClause() {
		return testClause;
	}

	@Override
	public void setCondition(final ConditionValueStmt condition) {
		this.condition = condition;
	}

}
