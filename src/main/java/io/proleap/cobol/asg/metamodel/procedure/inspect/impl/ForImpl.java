/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.InspectAllLeadingContext;
import io.proleap.cobol.CobolParser.InspectAllLeadingsContext;
import io.proleap.cobol.CobolParser.InspectBeforeAfterContext;
import io.proleap.cobol.CobolParser.InspectCharactersContext;
import io.proleap.cobol.CobolParser.InspectForContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.inspect.AllLeadingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.inspect.Characters;
import io.proleap.cobol.asg.metamodel.procedure.inspect.For;

public class ForImpl extends InspectPhraseImpl implements For {

	protected List<AllLeadingPhrase> allLeadingPhrase = new ArrayList<AllLeadingPhrase>();

	protected List<Characters> characters = new ArrayList<Characters>();

	protected final InspectForContext ctx;

	protected Call tallyCountDataItemCall;

	public ForImpl(final ProgramUnit programUnit, final InspectForContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AllLeadingPhrase addAllLeadingPhrase(final InspectAllLeadingsContext ctx) {
		AllLeadingPhrase result = (AllLeadingPhrase) getASGElement(ctx);

		if (result == null) {
			result = new AllLeadingPhraseImpl(programUnit, ctx);

			// type
			final AllLeadingPhrase.AllLeadingsType type;

			if (ctx.ALL() != null) {
				type = AllLeadingPhrase.AllLeadingsType.ALL;
			} else if (ctx.LEADING() != null) {
				type = AllLeadingPhrase.AllLeadingsType.LEADING;
			} else {
				type = null;
			}

			result.setAllLeadingsType(type);

			// all leadings
			for (final InspectAllLeadingContext inspectAllLeadingContext : ctx.inspectAllLeading()) {
				result.addAllLeading(inspectAllLeadingContext);
			}

			allLeadingPhrase.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Characters addCharacters(final InspectCharactersContext ctx) {
		Characters result = (Characters) getASGElement(ctx);

		if (result == null) {
			result = new CharactersImpl(programUnit, ctx);

			// before / after
			for (final InspectBeforeAfterContext inspectBeforeAfterContext : ctx.inspectBeforeAfter()) {
				result.addBeforeAfterPhrase(inspectBeforeAfterContext);
			}

			characters.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<AllLeadingPhrase> getAllLeadingPhrase() {
		return allLeadingPhrase;
	}

	@Override
	public List<Characters> getCharacters() {
		return characters;
	}

	@Override
	public Call getTallyCountDataItemCall() {
		return tallyCountDataItemCall;
	}

	@Override
	public void setTallyCountDataItemCall(final Call tallyCountDataItemCall) {
		this.tallyCountDataItemCall = tallyCountDataItemCall;
	}

}
