/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import io.proleap.cobol.Cobol85Parser.PerformAfterContext;
import io.proleap.cobol.Cobol85Parser.PerformTestClauseContext;
import io.proleap.cobol.Cobol85Parser.PerformVaryingClauseContext;
import io.proleap.cobol.Cobol85Parser.PerformVaryingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.TestClause;
import io.proleap.cobol.asg.metamodel.procedure.perform.Varying;
import io.proleap.cobol.asg.metamodel.procedure.perform.VaryingClause;

public class VaryingImpl extends CobolDivisionElementImpl implements Varying {

	protected final PerformVaryingContext ctx;

	protected TestClause testClause;

	protected VaryingClause varyingClause;

	public VaryingImpl(final ProgramUnit programUnit, final PerformVaryingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public TestClause addTestClause(final PerformTestClauseContext ctx) {
		TestClause result = (TestClause) getASGElement(ctx);

		if (result == null) {
			result = new TestClauseImpl(programUnit, ctx);

			final TestClause.Type type;

			if (ctx.BEFORE() != null) {
				type = TestClause.Type.BEFORE;
			} else if (ctx.AFTER() != null) {
				type = TestClause.Type.AFTER;
			} else {
				type = null;
			}

			result.setType(type);

			testClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public VaryingClause addVaryingClause(final PerformVaryingClauseContext ctx) {
		VaryingClause result = (VaryingClause) getASGElement(ctx);

		if (result == null) {
			result = new VaryingClauseImpl(programUnit, ctx);

			// varying
			result.addVaryingPhrase(ctx.performVaryingPhrase());

			// after
			for (final PerformAfterContext performAfterContext : ctx.performAfter()) {
				result.addAfter(performAfterContext);
			}

			varyingClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public TestClause getTestClause() {
		return testClause;
	}

	@Override
	public VaryingClause getVaryingClause() {
		return varyingClause;
	}

}
