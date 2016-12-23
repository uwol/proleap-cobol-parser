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

import io.proleap.cobol.Cobol85Parser.InspectBeforeAfterContext;
import io.proleap.cobol.Cobol85Parser.InspectReplacingAllLeadingContext;
import io.proleap.cobol.Cobol85Parser.InspectReplacingAllLeadingsContext;
import io.proleap.cobol.Cobol85Parser.InspectReplacingCharactersContext;
import io.proleap.cobol.Cobol85Parser.InspectReplacingPhraseContext;
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
			final ReplacingAllLeadings.Type type;

			if (ctx.ALL() != null) {
				type = ReplacingAllLeadings.Type.ALL;
			} else if (ctx.LEADING() != null) {
				type = ReplacingAllLeadings.Type.LEADING;
			} else if (ctx.FIRST() != null) {
				type = ReplacingAllLeadings.Type.FIRST;
			} else {
				type = null;
			}

			result.setType(type);

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
				result.addBeforeAfter(inspectBeforeAfterContext);
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
