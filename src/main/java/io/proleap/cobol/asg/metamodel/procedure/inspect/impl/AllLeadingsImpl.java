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
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.inspect.AllLeading;
import io.proleap.cobol.asg.metamodel.procedure.inspect.AllLeadings;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class AllLeadingsImpl extends InspectPhraseImpl implements AllLeadings {

	protected List<AllLeading> allLeadings = new ArrayList<AllLeading>();

	protected final InspectAllLeadingsContext ctx;

	protected Type type;

	public AllLeadingsImpl(final ProgramUnit programUnit, final InspectAllLeadingsContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AllLeading addAllLeading(final InspectAllLeadingContext ctx) {
		AllLeading result = (AllLeading) getASGElement(ctx);

		if (result == null) {
			result = new AllLeadingImpl(programUnit, ctx);

			// pattern
			final ValueStmt patterndDataItemValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setPatternDataItemValueStmt(patterndDataItemValueStmt);

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
	public List<AllLeading> getAllLeadings() {
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
