/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.InspectAllLeadingContext;
import io.proleap.cobol.Cobol85Parser.InspectAllLeadingsContext;
import io.proleap.cobol.Cobol85Parser.InspectBeforeAfterContext;
import io.proleap.cobol.Cobol85Parser.InspectCharactersContext;
import io.proleap.cobol.Cobol85Parser.InspectForContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.inspect.AllLeadings;
import io.proleap.cobol.asg.metamodel.procedure.inspect.Characters;
import io.proleap.cobol.asg.metamodel.procedure.inspect.For;

public class ForImpl extends InspectPhraseImpl implements For {

	protected List<AllLeadings> allLeadings = new ArrayList<AllLeadings>();

	protected List<Characters> characters = new ArrayList<Characters>();

	protected final InspectForContext ctx;

	protected Call tallyCountDataItemCall;

	public ForImpl(final ProgramUnit programUnit, final InspectForContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AllLeadings addAllLeadings(final InspectAllLeadingsContext ctx) {
		AllLeadings result = (AllLeadings) getASGElement(ctx);

		if (result == null) {
			result = new AllLeadingsImpl(programUnit, ctx);

			// type
			final AllLeadings.Type type;

			if (ctx.ALL() != null) {
				type = AllLeadings.Type.All;
			} else if (ctx.LEADING() != null) {
				type = AllLeadings.Type.Leading;
			} else {
				type = null;
			}

			result.setType(type);

			// all leadings
			for (final InspectAllLeadingContext inspectAllLeadingContext : ctx.inspectAllLeading()) {
				result.addAllLeading(inspectAllLeadingContext);
			}

			allLeadings.add(result);
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
				result.addBeforeAfter(inspectBeforeAfterContext);
			}

			characters.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<AllLeadings> getAllLeadings() {
		return allLeadings;
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
