/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort.impl;

import io.proleap.cobol.CobolParser.SortOutputProcedurePhraseContext;
import io.proleap.cobol.CobolParser.SortOutputThroughContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.sort.OutputProcedure;
import io.proleap.cobol.asg.metamodel.procedure.sort.OutputThrough;

public class OutputProcedureImpl extends CobolDivisionElementImpl implements OutputProcedure {

	protected final SortOutputProcedurePhraseContext ctx;

	protected OutputThrough outputThrough;

	protected Call procedureCall;

	public OutputProcedureImpl(final ProgramUnit programUnit, final SortOutputProcedurePhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public OutputThrough addOutputThrough(final SortOutputThroughContext ctx) {
		OutputThrough result = (OutputThrough) getASGElement(ctx);

		if (result == null) {
			result = new OutputThroughImpl(programUnit, ctx);

			// procedure call
			final Call procedureCall = createCall(ctx.procedureName());
			result.setProcedureCall(procedureCall);

			outputThrough = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OutputThrough getOutputThrough() {
		return outputThrough;
	}

	@Override
	public Call getProcedureCall() {
		return procedureCall;
	}

	@Override
	public void setProcedureCall(final Call procedureCall) {
		this.procedureCall = procedureCall;
	}

}
