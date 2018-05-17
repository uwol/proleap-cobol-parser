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

import io.proleap.cobol.CobolParser.InspectBeforeAfterContext;
import io.proleap.cobol.CobolParser.InspectReplacingAllLeadingContext;
import io.proleap.cobol.CobolParser.InspectReplacingAllLeadingsContext;
import io.proleap.cobol.CobolParser.InspectReplacingCharactersContext;
import io.proleap.cobol.CobolParser.InspectReplacingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.inspect.Replacing;
import io.proleap.cobol.asg.metamodel.procedure.inspect.ReplacingAllLeadings;
import io.proleap.cobol.asg.metamodel.procedure.inspect.ReplacingCharacters;

public class ReplacingImpl extends InspectPhraseImpl implements Replacing {

	protected List<ReplacingAllLeadings> allLeadings = new ArrayList<ReplacingAllLeadings>();

	protected List<ReplacingCharacters> characters = new ArrayList<ReplacingCharacters>();

	protected final InspectReplacingPhraseContext ctx;

	public ReplacingImpl(final ProgramUnit programUnit, final InspectReplacingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ReplacingAllLeadings addAllLeadings(final InspectReplacingAllLeadingsContext ctx) {
		ReplacingAllLeadings result = (ReplacingAllLeadings) getASGElement(ctx);

		if (result == null) {
			result = new ReplacingAllLeadingsImpl(programUnit, ctx);

			// type
			final ReplacingAllLeadings.ReplacingAllLeadingsType type;

			if (ctx.ALL() != null) {
				type = ReplacingAllLeadings.ReplacingAllLeadingsType.ALL;
			} else if (ctx.LEADING() != null) {
				type = ReplacingAllLeadings.ReplacingAllLeadingsType.LEADING;
			} else if (ctx.FIRST() != null) {
				type = ReplacingAllLeadings.ReplacingAllLeadingsType.FIRST;
			} else {
				type = null;
			}

			result.setReplacingAllLeadingsType(type);

			// all leadings
			for (final InspectReplacingAllLeadingContext inspectReplacingAllLeadingContext : ctx
					.inspectReplacingAllLeading()) {
				result.addAllLeading(inspectReplacingAllLeadingContext);
			}

			allLeadings.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ReplacingCharacters addCharacters(final InspectReplacingCharactersContext ctx) {
		ReplacingCharacters result = (ReplacingCharacters) getASGElement(ctx);

		if (result == null) {
			result = new ReplacingCharactersImpl(programUnit, ctx);

			// by
			result.addBy(ctx.inspectBy());

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
	public List<ReplacingAllLeadings> getAllLeadings() {
		return allLeadings;
	}

	@Override
	public List<ReplacingCharacters> getCharacters() {
		return characters;
	}
}
