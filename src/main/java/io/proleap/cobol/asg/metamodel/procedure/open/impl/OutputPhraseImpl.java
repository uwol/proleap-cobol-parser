/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.open.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.OpenOutputContext;
import io.proleap.cobol.CobolParser.OpenOutputStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.open.OutputPhrase;
import io.proleap.cobol.asg.metamodel.procedure.open.Output;

public class OutputPhraseImpl extends CobolDivisionElementImpl implements OutputPhrase {

	protected final OpenOutputStatementContext ctx;

	protected List<Output> outputs = new ArrayList<Output>();

	public OutputPhraseImpl(final ProgramUnit programUnit, final OpenOutputStatementContext ctx) {
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
