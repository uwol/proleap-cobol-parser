/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.subtract.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.SubtractFromGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.SubtractGivingContext;
import io.proleap.cobol.Cobol85Parser.SubtractMinuendGivingContext;
import io.proleap.cobol.Cobol85Parser.SubtractSubtrahendContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.subtract.Giving;
import io.proleap.cobol.parser.metamodel.procedure.subtract.MinuendGiving;
import io.proleap.cobol.parser.metamodel.procedure.subtract.SubtractFromGiving;
import io.proleap.cobol.parser.metamodel.procedure.subtract.Subtrahend;

public class SubtractFromGivingImpl extends StatementImpl implements SubtractFromGiving {

	protected final SubtractFromGivingStatementContext ctx;

	protected List<Giving> givings = new ArrayList<Giving>();

	protected MinuendGiving minuend;

	protected List<Subtrahend> subtrahends = new ArrayList<Subtrahend>();

	public SubtractFromGivingImpl(final ProgramUnit programUnit, final SubtractFromGivingStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Giving addGiving(final SubtractGivingContext ctx) {
		Giving result = (Giving) getASGElement(ctx);

		if (result == null) {
			result = new GivingImpl(programUnit, ctx);

			// giving call
			final Call givingCall = createCall(ctx.identifier());
			result.setGivingCall(givingCall);

			// rounded
			if (ctx.ROUNDED() != null) {
				result.setRounded(true);
			}

			givings.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MinuendGiving addMinuend(final SubtractMinuendGivingContext ctx) {
		MinuendGiving result = (MinuendGiving) getASGElement(ctx);

		if (result == null) {
			result = new MinuendGivingImpl(programUnit, ctx);

			// minuend
			final Call minuendCall = createCall(ctx.identifier());
			result.setMinuendCall(minuendCall);

			minuend = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Subtrahend addSubtrahend(final SubtractSubtrahendContext ctx) {
		Subtrahend result = (Subtrahend) getASGElement(ctx);

		if (result == null) {
			result = new SubtrahendImpl(programUnit, ctx);

			// subtrahend
			final Call subtrahendCall = createCall(ctx.identifier(), ctx.literal());
			result.setSubtrahendCall(subtrahendCall);

			subtrahends.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Giving> getGivings() {
		return givings;
	}

	@Override
	public MinuendGiving getMinuend() {
		return minuend;
	}

	@Override
	public List<Subtrahend> getSubtrahends() {
		return subtrahends;
	}

}
