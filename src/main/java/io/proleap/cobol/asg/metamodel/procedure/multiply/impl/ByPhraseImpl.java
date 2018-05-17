/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.multiply.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.MultiplyRegularContext;
import io.proleap.cobol.CobolParser.MultiplyRegularOperandContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.multiply.ByOperand;
import io.proleap.cobol.asg.metamodel.procedure.multiply.ByPhrase;

public class ByPhraseImpl extends CobolDivisionElementImpl implements ByPhrase {

	protected List<ByOperand> byOperands = new ArrayList<ByOperand>();

	protected final MultiplyRegularContext ctx;

	public ByPhraseImpl(final ProgramUnit programUnit, final MultiplyRegularContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ByOperand addOperand(final MultiplyRegularOperandContext ctx) {
		ByOperand result = (ByOperand) getASGElement(ctx);

		if (result == null) {
			result = new ByOperandImpl(programUnit, ctx);

			// call
			if (ctx.identifier() != null) {
				final Call operandCall = createCall(ctx.identifier());
				result.setOperandCall(operandCall);
			}

			// rounded
			if (ctx.ROUNDED() != null) {
				result.setRounded(true);
			}

			byOperands.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<ByOperand> getByOperands() {
		return byOperands;
	}
}
