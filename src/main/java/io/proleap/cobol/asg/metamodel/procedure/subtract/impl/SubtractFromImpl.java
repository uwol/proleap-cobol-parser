/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.subtract.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.SubtractFromStatementContext;
import io.proleap.cobol.Cobol85Parser.SubtractMinuendContext;
import io.proleap.cobol.Cobol85Parser.SubtractSubtrahendContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.subtract.Minuend;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractFrom;
import io.proleap.cobol.asg.metamodel.procedure.subtract.Subtrahend;

public class SubtractFromImpl extends CobolDivisionElementImpl implements SubtractFrom {

	protected final SubtractFromStatementContext ctx;

	protected List<Minuend> minuends = new ArrayList<Minuend>();

	protected List<Subtrahend> subtrahends = new ArrayList<Subtrahend>();

	public SubtractFromImpl(final ProgramUnit programUnit, final SubtractFromStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Minuend addMinuend(final SubtractMinuendContext ctx) {
		Minuend result = (Minuend) getASGElement(ctx);

		if (result == null) {
			result = new MinuendImpl(programUnit, ctx);

			// minuend
			final Call minuendCall = createCall(ctx.identifier());
			result.setMinuendCall(minuendCall);

			// rounded
			if (ctx.ROUNDED() != null) {
				result.setRounded(true);
			}

			minuends.add(result);
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
	public List<Minuend> getMinuends() {
		return minuends;
	}

	@Override
	public List<Subtrahend> getSubtrahends() {
		return subtrahends;
	}

}
