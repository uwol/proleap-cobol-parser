/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.SortInputProcedurePhraseContext;
import io.proleap.cobol.CobolParser.SortInputThroughContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.sort.InputProcedure;
import io.proleap.cobol.asg.metamodel.procedure.sort.InputThrough;

public class InputProcedureImpl extends CobolDivisionElementImpl implements InputProcedure {

	protected final List<Call> calls = new ArrayList<>();

	protected final SortInputProcedurePhraseContext ctx;

	protected InputThrough inputThrough;

	protected Call procedureCall;

	public InputProcedureImpl(final ProgramUnit programUnit, final SortInputProcedurePhraseContext ctx) {
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
	public InputThrough addInputThrough(final SortInputThroughContext ctx) {
		InputThrough result = (InputThrough) getASGElement(ctx);

		if (result == null) {
			result = new InputThroughImpl(programUnit, ctx);

			// procedure call
			final Call lastCall = createCall(ctx.procedureName());
			result.setProcedureCall(lastCall);

			inputThrough = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Call> getCalls() {
		return calls;
	}

	@Override
	public InputThrough getInputThrough() {
		return inputThrough;
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
