/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.open.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.OpenInputContext;
import io.proleap.cobol.Cobol85Parser.OpenInputStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.open.Input;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenInput;

public class OpenInputImpl extends CobolDivisionElementImpl implements OpenInput {

	protected final OpenInputStatementContext ctx;

	protected List<Input> inputs = new ArrayList<Input>();

	public OpenInputImpl(final ProgramUnit programUnit, final OpenInputStatementContext ctx) {
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
			final Input.Type type;

			if (ctx.REVERSED() != null) {
				type = Input.Type.REVERSED;
			} else if (ctx.REWIND() != null) {
				type = Input.Type.NO_REWIND;
			} else {
				type = null;
			}

			result.setType(type);

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
