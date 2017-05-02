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
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.inspect.ReplacingAllLeading;
import io.proleap.cobol.asg.metamodel.procedure.inspect.ReplacingAllLeadings;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ReplacingAllLeadingsImpl extends InspectPhraseImpl implements ReplacingAllLeadings {

	protected List<ReplacingAllLeading> allLeadings = new ArrayList<ReplacingAllLeading>();

	protected final InspectReplacingAllLeadingsContext ctx;

	protected Type type;

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
				result.addBeforeAfter(inspectBeforeAfterContext);
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
	public Type getType() {
		return type;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
