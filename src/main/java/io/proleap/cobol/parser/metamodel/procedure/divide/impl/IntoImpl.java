/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.divide.impl;

import io.proleap.cobol.Cobol85Parser.DivideGivingContext;
import io.proleap.cobol.Cobol85Parser.DivideGivingPhraseContext;
import io.proleap.cobol.Cobol85Parser.DivideIntoStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.divide.Givings;
import io.proleap.cobol.parser.metamodel.procedure.divide.Into;

public class IntoImpl extends CobolDivisionElementImpl implements Into {

	protected DivideIntoStatementContext ctx;

	protected Givings givings;

	protected Call intoCall;

	public IntoImpl(final ProgramUnit programUnit, final DivideIntoStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Givings addGivings(final DivideGivingPhraseContext ctx) {
		Givings result = (Givings) getASGElement(ctx);

		if (result == null) {
			result = new GivingsImpl(programUnit, ctx);

			// givings
			for (final DivideGivingContext divideGivingContext : ctx.divideGiving()) {
				result.addGiving(divideGivingContext);
			}

			givings = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Givings getGivings() {
		return givings;
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
