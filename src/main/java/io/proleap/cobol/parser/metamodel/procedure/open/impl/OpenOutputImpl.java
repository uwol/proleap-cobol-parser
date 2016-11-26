/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.open.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.OpenOutputContext;
import io.proleap.cobol.Cobol85Parser.OpenOutputStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.open.OpenOutput;
import io.proleap.cobol.parser.metamodel.procedure.open.Output;

public class OpenOutputImpl extends CobolDivisionElementImpl implements OpenOutput {

	protected final OpenOutputStatementContext ctx;

	protected List<Output> outputs = new ArrayList<Output>();

	public OpenOutputImpl(final ProgramUnit programUnit, final OpenOutputStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Output addOpenOutput(final OpenOutputContext ctx) {
		Output result = (Output) getASGElement(ctx);

		if (result == null) {
			result = new OutputImpl(programUnit, ctx);

			// file
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			// no rewind
			if (ctx.REWIND() != null) {
				result.setNoRewind(true);
			}

			outputs.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Output> getOutputs() {
		return outputs;
	}

}
