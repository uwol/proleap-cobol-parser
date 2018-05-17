/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import io.proleap.cobol.CobolParser.PerformByContext;
import io.proleap.cobol.CobolParser.PerformFromContext;
import io.proleap.cobol.CobolParser.PerformUntilContext;
import io.proleap.cobol.CobolParser.PerformVaryingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.ByPhrase;
import io.proleap.cobol.asg.metamodel.procedure.perform.FromPhrase;
import io.proleap.cobol.asg.metamodel.procedure.perform.Until;
import io.proleap.cobol.asg.metamodel.procedure.perform.VaryingPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class VaryingPhraseImpl extends CobolDivisionElementImpl implements VaryingPhrase {

	protected ByPhrase byPhrase;

	protected final PerformVaryingPhraseContext ctx;

	protected FromPhrase fromPhrase;

	protected Until until;

	protected ValueStmt varyingValueStmt;

	public VaryingPhraseImpl(final ProgramUnit programUnit, final PerformVaryingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ByPhrase addByPhrase(final PerformByContext ctx) {
		ByPhrase result = (ByPhrase) getASGElement(ctx);

		if (result == null) {
			result = new ByPhraseImpl(programUnit, ctx);

			final ValueStmt byValueStmt = createValueStmt(ctx.identifier(), ctx.literal(), ctx.arithmeticExpression());
			result.setByValueStmt(byValueStmt);

			byPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public FromPhrase addFromPhrase(final PerformFromContext ctx) {
		FromPhrase result = (FromPhrase) getASGElement(ctx);

		if (result == null) {
			result = new FromPhraseImpl(programUnit, ctx);

			final ValueStmt fromValueStmt = createValueStmt(ctx.identifier(), ctx.literal(),
					ctx.arithmeticExpression());
			result.setFromValueStmt(fromValueStmt);

			fromPhrase = result;
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
	public ByPhrase getBy() {
		return byPhrase;
	}

	@Override
	public FromPhrase getFrom() {
		return fromPhrase;
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
