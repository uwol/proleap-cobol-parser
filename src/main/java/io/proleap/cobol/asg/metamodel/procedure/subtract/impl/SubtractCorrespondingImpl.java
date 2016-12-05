/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.subtract.impl;

import io.proleap.cobol.Cobol85Parser.SubtractCorrespondingStatementContext;
import io.proleap.cobol.Cobol85Parser.SubtractMinuendCorrespondingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.subtract.MinuendCorresponding;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractCorresponding;

public class SubtractCorrespondingImpl extends CobolDivisionElementImpl implements SubtractCorresponding {

	protected final SubtractCorrespondingStatementContext ctx;

	protected MinuendCorresponding minuend;

	protected Call subtrahendCall;

	public SubtractCorrespondingImpl(final ProgramUnit programUnit, final SubtractCorrespondingStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public MinuendCorresponding addMinuend(final SubtractMinuendCorrespondingContext ctx) {
		MinuendCorresponding result = (MinuendCorresponding) getASGElement(ctx);

		if (result == null) {
			result = new MinuendCorrespondingImpl(programUnit, ctx);

			// minuend
			final Call minuendCall = createCall(ctx.qualifiedDataName());
			result.setMinuendCall(minuendCall);

			// rounded
			if (ctx.ROUNDED() != null) {
				result.setRounded(true);
			}

			minuend = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MinuendCorresponding getMinuend() {
		return minuend;
	}

	@Override
	public Call getSubtrahendCall() {
		return subtrahendCall;
	}

	@Override
	public void setSubtrahendCall(final Call subtrahendCall) {
		this.subtrahendCall = subtrahendCall;
	}

}
