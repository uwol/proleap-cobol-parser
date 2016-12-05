/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.DivideGivingContext;
import io.proleap.cobol.Cobol85Parser.DivideIntoGivingStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.divide.Giving;
import io.proleap.cobol.asg.metamodel.procedure.divide.IntoGiving;

public class IntoGivingImpl extends CobolDivisionElementImpl implements IntoGiving {

	protected DivideIntoGivingStatementContext ctx;

	protected List<Giving> givings = new ArrayList<Giving>();

	public IntoGivingImpl(final ProgramUnit programUnit, final DivideIntoGivingStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Giving addGiving(final DivideGivingContext ctx) {
		Giving result = (Giving) getASGElement(ctx);

		if (result == null) {
			result = new GivingImpl(programUnit, ctx);

			// call
			final Call call = createCall(ctx.identifier());
			result.setCall(call);

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
	public List<Giving> getGivings() {
		return givings;
	}

}
