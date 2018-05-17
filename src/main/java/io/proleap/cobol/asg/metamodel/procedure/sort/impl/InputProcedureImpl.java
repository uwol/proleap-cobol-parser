/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort.impl;

import io.proleap.cobol.CobolParser.SortInputProcedurePhraseContext;
import io.proleap.cobol.CobolParser.SortInputThroughContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.sort.InputProcedure;
import io.proleap.cobol.asg.metamodel.procedure.sort.InputThrough;

public class InputProcedureImpl extends CobolDivisionElementImpl implements InputProcedure {

	protected final SortInputProcedurePhraseContext ctx;

	protected InputThrough inputThrough;

	protected Call procedureCall;

	public InputProcedureImpl(final ProgramUnit programUnit, final SortInputProcedurePhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public InputThrough addInputThrough(final SortInputThroughContext ctx) {
		InputThrough result = (InputThrough) getASGElement(ctx);

		if (result == null) {
			result = new InputThroughImpl(programUnit, ctx);

			// procedure call
			final Call procedureCall = createCall(ctx.procedureName());
			result.setProcedureCall(procedureCall);

			inputThrough = result;
			registerASGElement(result);
		}

		return result;
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
