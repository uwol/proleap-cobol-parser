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

import io.proleap.cobol.CobolParser.InspectForContext;
import io.proleap.cobol.CobolParser.InspectReplacingAllLeadingsContext;
import io.proleap.cobol.CobolParser.InspectReplacingCharactersContext;
import io.proleap.cobol.CobolParser.InspectReplacingPhraseContext;
import io.proleap.cobol.CobolParser.InspectTallyingReplacingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.inspect.For;
import io.proleap.cobol.asg.metamodel.procedure.inspect.Replacing;
import io.proleap.cobol.asg.metamodel.procedure.inspect.TallyingReplacing;

public class TallyingReplacingImpl extends InspectPhraseImpl implements TallyingReplacing {

	protected final InspectTallyingReplacingPhraseContext ctx;

	protected List<For> fors = new ArrayList<For>();

	protected List<Replacing> replacings = new ArrayList<Replacing>();

	public TallyingReplacingImpl(final ProgramUnit programUnit, final InspectTallyingReplacingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public For addFor(final InspectForContext ctx) {
		For result = (For) getASGElement(ctx);

		if (result == null) {
			result = createFor(ctx);
			fors.add(result);
		}

		return result;
	}

	@Override
	public Replacing addReplacing(final InspectReplacingPhraseContext ctx) {
		Replacing result = (Replacing) getASGElement(ctx);

		if (result == null) {
			result = new ReplacingImpl(programUnit, ctx);

			// characters
			for (final InspectReplacingCharactersContext inspectReplacingCharactersContext : ctx
					.inspectReplacingCharacters()) {
				result.addCharacters(inspectReplacingCharactersContext);
			}

			// replacing all leadings
			for (final InspectReplacingAllLeadingsContext inspectReplacingAllLeadingsContext : ctx
					.inspectReplacingAllLeadings()) {
				result.addAllLeadings(inspectReplacingAllLeadingsContext);
			}

			replacings.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<For> getFors() {
		return fors;
	}

	@Override
	public List<Replacing> getReplacings() {
		return replacings;
	}

}
