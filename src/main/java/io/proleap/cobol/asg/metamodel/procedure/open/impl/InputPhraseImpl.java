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

import io.proleap.cobol.CobolParser.OpenInputContext;
import io.proleap.cobol.CobolParser.OpenInputStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.open.Input;
import io.proleap.cobol.asg.metamodel.procedure.open.InputPhrase;

public class InputPhraseImpl extends CobolDivisionElementImpl implements InputPhrase {

	protected final OpenInputStatementContext ctx;

	protected List<Input> inputs = new ArrayList<Input>();

	public InputPhraseImpl(final ProgramUnit programUnit, final OpenInputStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Input addOpenInput(final OpenInputContext ctx) {
		Input result = (Input) getASGElement(ctx);

		if (result == null) {
			result = new InputImpl(programUnit, ctx);

			// file
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			// type
			final Input.InputType type;

			if (ctx.REVERSED() != null) {
				type = Input.InputType.REVERSED;
			} else if (ctx.REWIND() != null) {
				type = Input.InputType.NO_REWIND;
			} else {
				type = null;
			}

			result.setInputType(type);

			inputs.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Input> getInputs() {
		return inputs;
	}

}
