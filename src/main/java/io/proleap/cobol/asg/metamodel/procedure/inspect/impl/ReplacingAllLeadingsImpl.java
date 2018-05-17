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
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.inspect.ReplacingAllLeading;
import io.proleap.cobol.asg.metamodel.procedure.inspect.ReplacingAllLeadings;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ReplacingAllLeadingsImpl extends InspectPhraseImpl implements ReplacingAllLeadings {

	protected List<ReplacingAllLeading> allLeadings = new ArrayList<ReplacingAllLeading>();

	protected final InspectReplacingAllLeadingsContext ctx;

	protected ReplacingAllLeadingsType replacingAllLeadingsType;

	public ReplacingAllLeadingsImpl(final ProgramUnit programUnit, final InspectReplacingAllLeadingsContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ReplacingAllLeading addAllLeading(final InspectReplacingAllLeadingContext ctx) {
		ReplacingAllLeading result = (ReplacingAllLeading) getASGElement(ctx);

		if (result == null) {
			result = new ReplacingAllLeadingImpl(programUnit, ctx);

			// pattern
			final ValueStmt patterndDataItemValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setPatternDataItemValueStmt(patterndDataItemValueStmt);

			// by
			result.addBy(ctx.inspectBy());

			// before / after
			for (final InspectBeforeAfterContext inspectBeforeAfterContext : ctx.inspectBeforeAfter()) {
				result.addBeforeAfterPhrase(inspectBeforeAfterContext);
			}

			allLeadings.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<ReplacingAllLeading> getAllLeadings() {
		return allLeadings;
	}

	@Override
	public ReplacingAllLeadingsType getReplacingAllLeadingsType() {
		return replacingAllLeadingsType;
	}

	@Override
	public void setReplacingAllLeadingsType(final ReplacingAllLeadingsType replacingAllLeadingsType) {
		this.replacingAllLeadingsType = replacingAllLeadingsType;
	}

}
