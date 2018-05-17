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

import io.proleap.cobol.CobolParser.MultiplyGivingContext;
import io.proleap.cobol.CobolParser.MultiplyGivingOperandContext;
import io.proleap.cobol.CobolParser.MultiplyGivingResultContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.multiply.GivingOperand;
import io.proleap.cobol.asg.metamodel.procedure.multiply.GivingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.multiply.GivingResult;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class GivingPhraseImpl extends CobolDivisionElementImpl implements GivingPhrase {

	protected final MultiplyGivingContext ctx;

	protected GivingOperand givingOperand;

	protected List<GivingResult> results = new ArrayList<GivingResult>();

	public GivingPhraseImpl(final ProgramUnit programUnit, final MultiplyGivingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public GivingOperand addGivingOperand(final MultiplyGivingOperandContext ctx) {
		GivingOperand result = (GivingOperand) getASGElement(ctx);

		if (result == null) {
			result = new GivingOperandImpl(programUnit, ctx);

			// operand call
			final ValueStmt operandValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setOperandValueStmt(operandValueStmt);

			givingOperand = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public GivingResult addGivingResult(final MultiplyGivingResultContext ctx) {
		GivingResult result = (GivingResult) getASGElement(ctx);

		if (result == null) {
			result = new GivingResultImpl(programUnit, ctx);

			// result
			if (ctx.identifier() != null) {
				final Call resultCall = createCall(ctx.identifier());
				result.setResultCall(resultCall);
			}

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
	public GivingOperand getGivingOperand() {
		return givingOperand;
	}

	@Override
	public List<GivingResult> getGivingResults() {
		return results;
	}

}
