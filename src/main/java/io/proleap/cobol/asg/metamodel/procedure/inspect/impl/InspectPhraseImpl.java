/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.CobolParser.InspectAllLeadingsContext;
import io.proleap.cobol.CobolParser.InspectBeforeAfterContext;
import io.proleap.cobol.CobolParser.InspectByContext;
import io.proleap.cobol.CobolParser.InspectCharactersContext;
import io.proleap.cobol.CobolParser.InspectForContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.inspect.BeforeAfterPhrase;
import io.proleap.cobol.asg.metamodel.procedure.inspect.By;
import io.proleap.cobol.asg.metamodel.procedure.inspect.For;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public abstract class InspectPhraseImpl extends CobolDivisionElementImpl {

	public InspectPhraseImpl(final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(programUnit, ctx);
	}

	protected BeforeAfterPhrase createBeforeAfterPhrase(final InspectBeforeAfterContext ctx) {
		BeforeAfterPhrase result = (BeforeAfterPhrase) getASGElement(ctx);

		if (result == null) {
			result = new BeforeAfterPhraseImpl(programUnit, ctx);

			// type
			final BeforeAfterPhrase.BeforeAfterType type;

			if (ctx.BEFORE() != null) {
				type = BeforeAfterPhrase.BeforeAfterType.BEFORE;
			} else if (ctx.AFTER() != null) {
				type = BeforeAfterPhrase.BeforeAfterType.AFTER;
			} else {
				type = null;
			}

			result.setBeforeAfterType(type);

			// data item
			final ValueStmt dataItemValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setDataItemValueStmt(dataItemValueStmt);

			registerASGElement(result);
		}

		return result;
	}

	protected By createBy(final InspectByContext ctx) {
		By result = (By) getASGElement(ctx);

		if (result == null) {
			result = new ByImpl(programUnit, ctx);

			// by
			final ValueStmt byValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setByValueStmt(byValueStmt);

			registerASGElement(result);
		}

		return result;
	}

	protected For createFor(final InspectForContext ctx) {
		For result = (For) getASGElement(ctx);

		if (result == null) {
			result = new ForImpl(programUnit, ctx);

			// tally count
			if (ctx.identifier() != null) {
				final Call tallyCountDataItemCall = createCall(ctx.identifier());
				result.setTallyCountDataItemCall(tallyCountDataItemCall);
			}

			// characters
			for (final InspectCharactersContext inspectCharactersContext : ctx.inspectCharacters()) {
				result.addCharacters(inspectCharactersContext);
			}

			// all leadings
			for (final InspectAllLeadingsContext inspectAllLeadingsContext : ctx.inspectAllLeadings()) {
				result.addAllLeadingPhrase(inspectAllLeadingsContext);
			}

			registerASGElement(result);
		}

		return result;
	}

}
