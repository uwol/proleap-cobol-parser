/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.use.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.UseDebugClauseContext;
import io.proleap.cobol.Cobol85Parser.UseDebugOnContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.use.Debug;
import io.proleap.cobol.asg.metamodel.procedure.use.DebugOn;

public class DebugImpl extends CobolDivisionElementImpl implements Debug {

	protected UseDebugClauseContext ctx;

	protected List<DebugOn> debugOns = new ArrayList<DebugOn>();

	public DebugImpl(final ProgramUnit programUnit, final UseDebugClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DebugOn addDebugOn(final UseDebugOnContext ctx) {
		DebugOn result = (DebugOn) getASGElement(ctx);

		if (result == null) {
			result = new DebugOnImpl(programUnit, ctx);

			// type and on call
			final DebugOn.Type type;
			final Call onCall;

			if (ctx.PROCEDURES() != null) {
				type = DebugOn.Type.AllProcedures;
				onCall = null;
			} else if (ctx.REFERENCES() != null) {
				type = DebugOn.Type.AllReferences;
				onCall = createCall(ctx.identifier());
			} else if (ctx.procedureName() != null) {
				type = DebugOn.Type.Procedure;
				onCall = createCall(ctx.procedureName());
			} else if (ctx.fileName() != null) {
				type = DebugOn.Type.File;
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
