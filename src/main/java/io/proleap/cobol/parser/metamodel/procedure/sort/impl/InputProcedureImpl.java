/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.sort.impl;

import io.proleap.cobol.Cobol85Parser.SortInputProcedurePhraseContext;
import io.proleap.cobol.Cobol85Parser.SortInputThroughContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.sort.InputProcedure;
import io.proleap.cobol.parser.metamodel.procedure.sort.InputThrough;

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
