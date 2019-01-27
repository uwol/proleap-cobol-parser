/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.merge.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.MergeOutputProcedurePhraseContext;
import io.proleap.cobol.CobolParser.MergeOutputThroughContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.merge.OutputProcedurePhrase;
import io.proleap.cobol.asg.metamodel.procedure.merge.OutputThrough;

public class OutputProcedurePhraseImpl extends CobolDivisionElementImpl implements OutputProcedurePhrase {

	protected final List<Call> calls = new ArrayList<>();

	protected final MergeOutputProcedurePhraseContext ctx;

	protected OutputThrough outputThrough;

	protected Call procedureCall;

	public OutputProcedurePhraseImpl(final ProgramUnit programUnit, final MergeOutputProcedurePhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addCall(final Call call) {
		calls.add(call);
	}

	@Override
	public void addCalls(final List<Call> calls) {
		this.calls.addAll(calls);
	}

	@Override
	public OutputThrough addOutputThrough(final MergeOutputThroughContext ctx) {
		OutputThrough result = (OutputThrough) getASGElement(ctx);

		if (result == null) {
			result = new OutputThroughImpl(programUnit, ctx);

			// procedure call
			final Call lastCall = createCall(ctx.procedureName());
			result.setProcedureCall(lastCall);

			outputThrough = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Call> getCalls() {
		return calls;
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
