/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.evaluate.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.EvaluateAlsoConditionContext;
import io.proleap.cobol.Cobol85Parser.EvaluateWhenContext;
import io.proleap.cobol.Cobol85Parser.EvaluateWhenPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.StatementsContainerImpl;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.When;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.WhenPhrase;

public class WhenPhraseImpl extends StatementsContainerImpl implements WhenPhrase {

	protected final EvaluateWhenPhraseContext ctx;

	protected List<When> whens = new ArrayList<When>();

	public WhenPhraseImpl(final ProgramUnit programUnit, final EvaluateWhenPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public When addWhen(final EvaluateWhenContext ctx) {
		When result = (When) getASGElement(ctx);

		if (result == null) {
			result = new WhenImpl(programUnit, ctx);

			// condition
			result.addCondition(ctx.evaluateCondition());

			// also conditions
			for (final EvaluateAlsoConditionContext evaluateAlsoConditionContext : ctx.evaluateAlsoCondition()) {
				result.addAlsoCondition(evaluateAlsoConditionContext);
			}

			whens.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<When> getWhens() {
		return whens;
	}

}
