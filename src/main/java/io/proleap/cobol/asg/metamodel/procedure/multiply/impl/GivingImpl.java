/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.multiply.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.MultiplyGivingContext;
import io.proleap.cobol.Cobol85Parser.MultiplyGivingOperandContext;
import io.proleap.cobol.Cobol85Parser.MultiplyGivingResultContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.multiply.Giving;
import io.proleap.cobol.asg.metamodel.procedure.multiply.GivingOperand;
import io.proleap.cobol.asg.metamodel.procedure.multiply.GivingResult;

public class GivingImpl extends CobolDivisionElementImpl implements Giving {

	protected final MultiplyGivingContext ctx;

	protected GivingOperand operand;

	protected List<GivingResult> results = new ArrayList<GivingResult>();

	public GivingImpl(final ProgramUnit programUnit, final MultiplyGivingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public GivingOperand addOperand(final MultiplyGivingOperandContext ctx) {
		GivingOperand result = (GivingOperand) getASGElement(ctx);

		if (result == null) {
			result = new GivingOperandImpl(programUnit, ctx);

			// operand call
			final Call operandCall = createCall(ctx.identifier(), ctx.literal());
			result.setOperandCall(operandCall);

			operand = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public GivingResult addResult(final MultiplyGivingResultContext ctx) {
		GivingResult result = (GivingResult) getASGElement(ctx);

		if (result == null) {
			result = new GivingResultImpl(programUnit, ctx);

			// result
			final Call resultCall = createCall(ctx.identifier());
			result.setResultCall(resultCall);

			// rounded
			if (ctx.ROUNDED() != null) {
				result.setRounded(true);
			}

			results.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public GivingOperand getOperand() {
		return operand;
	}

	@Override
	public List<GivingResult> getResults() {
		return results;
	}

}
