/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.use.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.UseDebugClauseContext;
import io.proleap.cobol.CobolParser.UseDebugOnContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.use.UseDebugStatement;
import io.proleap.cobol.asg.metamodel.procedure.use.DebugOn;

public class UseDebugStatementImpl extends CobolDivisionElementImpl implements UseDebugStatement {

	protected UseDebugClauseContext ctx;

	protected List<DebugOn> debugOns = new ArrayList<DebugOn>();

	public UseDebugStatementImpl(final ProgramUnit programUnit, final UseDebugClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DebugOn addDebugOn(final UseDebugOnContext ctx) {
		DebugOn result = (DebugOn) getASGElement(ctx);

		if (result == null) {
			result = new DebugOnImpl(programUnit, ctx);

			// type and on call
			final DebugOn.DebugOnType type;
			final Call onCall;

			if (ctx.PROCEDURES() != null) {
				type = DebugOn.DebugOnType.ALL_PROCEDURES;
				onCall = null;
			} else if (ctx.REFERENCES() != null) {
				type = DebugOn.DebugOnType.ALL_REFERENCES;
				onCall = createCall(ctx.identifier());
			} else if (ctx.procedureName() != null) {
				type = DebugOn.DebugOnType.PROCEDURE;
				onCall = createCall(ctx.procedureName());
			} else if (ctx.fileName() != null) {
				type = DebugOn.DebugOnType.FILE;
				onCall = createCall(ctx.fileName());
			} else {
				type = null;
				onCall = null;
			}

			result.setType(type);
			result.setOnCall(onCall);

			debugOns.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<DebugOn> getDebugOns() {
		return debugOns;
	}

}
