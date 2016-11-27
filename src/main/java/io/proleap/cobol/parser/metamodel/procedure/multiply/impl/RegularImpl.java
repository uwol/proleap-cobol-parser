/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.multiply.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.MultiplyRegularContext;
import io.proleap.cobol.Cobol85Parser.MultiplyRegularOperandContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.multiply.Regular;
import io.proleap.cobol.parser.metamodel.procedure.multiply.RegularOperand;

public class RegularImpl extends CobolDivisionElementImpl implements Regular {

	protected final MultiplyRegularContext ctx;

	protected List<RegularOperand> operands = new ArrayList<RegularOperand>();

	public RegularImpl(final ProgramUnit programUnit, final MultiplyRegularContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public RegularOperand addOperand(final MultiplyRegularOperandContext ctx) {
		RegularOperand result = (RegularOperand) getASGElement(ctx);

		if (result == null) {
			result = new RegularOperandImpl(programUnit, ctx);

			// call
			final Call operandCall = createCall(ctx.identifier());
			result.setOperandCall(operandCall);

			// rounded
			if (ctx.ROUNDED() != null) {
				result.setRounded(true);
			}

			operands.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<RegularOperand> getOperands() {
		return operands;
	}

}
