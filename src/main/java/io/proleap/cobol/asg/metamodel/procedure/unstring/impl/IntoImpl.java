/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring.impl;

import io.proleap.cobol.CobolParser.UnstringCountInContext;
import io.proleap.cobol.CobolParser.UnstringDelimiterInContext;
import io.proleap.cobol.CobolParser.UnstringIntoContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.unstring.CountIn;
import io.proleap.cobol.asg.metamodel.procedure.unstring.DelimiterIn;
import io.proleap.cobol.asg.metamodel.procedure.unstring.Into;

public class IntoImpl extends CobolDivisionElementImpl implements Into {

	protected CountIn countIn;

	protected final UnstringIntoContext ctx;

	protected DelimiterIn delimiterIn;

	protected Call intoCall;

	public IntoImpl(final ProgramUnit programUnit, final UnstringIntoContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public CountIn addCountIn(final UnstringCountInContext ctx) {
		CountIn result = (CountIn) getASGElement(ctx);

		if (result == null) {
			result = new CountInImpl(programUnit, ctx);

			if (ctx.identifier() != null) {
				final Call countInCall = createCall(ctx.identifier());
				result.setCountInCall(countInCall);
			}

			countIn = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DelimiterIn addDelimiterIn(final UnstringDelimiterInContext ctx) {
		DelimiterIn result = (DelimiterIn) getASGElement(ctx);

		if (result == null) {
			result = new DelimiterInImpl(programUnit, ctx);

			if (ctx.identifier() != null) {
				final Call delimiterInCall = createCall(ctx.identifier());
				result.setDelimiterInCall(delimiterInCall);
			}

			delimiterIn = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CountIn getCountIn() {
		return countIn;
	}

	@Override
	public DelimiterIn getDelimiterIn() {
		return delimiterIn;
	}

	@Override
	public Call getIntoCall() {
		return intoCall;
	}

	@Override
	public void setIntoCall(final Call intoCall) {
		this.intoCall = intoCall;
	}

}
