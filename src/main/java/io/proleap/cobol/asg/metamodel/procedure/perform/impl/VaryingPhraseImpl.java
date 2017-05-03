/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import io.proleap.cobol.Cobol85Parser.PerformByContext;
import io.proleap.cobol.Cobol85Parser.PerformFromContext;
import io.proleap.cobol.Cobol85Parser.PerformUntilContext;
import io.proleap.cobol.Cobol85Parser.PerformVaryingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.By;
import io.proleap.cobol.asg.metamodel.procedure.perform.From;
import io.proleap.cobol.asg.metamodel.procedure.perform.Until;
import io.proleap.cobol.asg.metamodel.procedure.perform.VaryingPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class VaryingPhraseImpl extends CobolDivisionElementImpl implements VaryingPhrase {

	protected By by;

	protected final PerformVaryingPhraseContext ctx;

	protected From from;

	protected Until until;

	protected ValueStmt varyingValueStmt;

	public VaryingPhraseImpl(final ProgramUnit programUnit, final PerformVaryingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public By addBy(final PerformByContext ctx) {
		By result = (By) getASGElement(ctx);

		if (result == null) {
			result = new ByImpl(programUnit, ctx);

			final ValueStmt byValueStmt = createValueStmt(ctx.identifier(), ctx.literal(), ctx.arithmeticExpression());
			result.setByValueStmt(byValueStmt);

			by = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public From addFrom(final PerformFromContext ctx) {
		From result = (From) getASGElement(ctx);

		if (result == null) {
			result = new FromImpl(programUnit, ctx);

			final ValueStmt fromValueStmt = createValueStmt(ctx.identifier(), ctx.literal(),
					ctx.arithmeticExpression());
			result.setFromValueStmt(fromValueStmt);

			from = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Until addUntil(final PerformUntilContext ctx) {
		Until result = (Until) getASGElement(ctx);

		if (result == null) {
			result = new UntilImpl(programUnit, ctx);

			// test clause
			if (ctx.performTestClause() != null) {
				result.addTestClause(ctx.performTestClause());
			}

			// condition
			final ConditionValueStmt condition = createConditionValueStmt(ctx.condition());
			result.setCondition(condition);

			until = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public By getBy() {
		return by;
	}

	@Override
	public From getFrom() {
		return from;
	}

	@Override
	public Until getUntil() {
		return until;
	}

	@Override
	public ValueStmt getVaryingValueStmt() {
		return varyingValueStmt;
	}

	@Override
	public void setVaryingValueStmt(final ValueStmt varyingValueStmt) {
		this.varyingValueStmt = varyingValueStmt;
	}

}
