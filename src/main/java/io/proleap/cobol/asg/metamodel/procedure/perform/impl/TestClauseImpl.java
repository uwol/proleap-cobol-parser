/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import io.proleap.cobol.CobolParser.PerformTestClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.TestClause;

public class TestClauseImpl extends CobolDivisionElementImpl implements TestClause {

	protected final PerformTestClauseContext ctx;

	protected TestClauseType testClauseType;

	public TestClauseImpl(final ProgramUnit programUnit, final PerformTestClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public TestClauseType getTestClauseType() {
		return testClauseType;
	}

	@Override
	public void setTestClauseType(final TestClauseType testClauseType) {
		this.testClauseType = testClauseType;
	}

}
