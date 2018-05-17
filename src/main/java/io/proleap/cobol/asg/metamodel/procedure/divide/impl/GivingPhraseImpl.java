/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.DivideGivingContext;
import io.proleap.cobol.CobolParser.DivideGivingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.divide.Giving;
import io.proleap.cobol.asg.metamodel.procedure.divide.GivingPhrase;

public class GivingPhraseImpl extends CobolDivisionElementImpl implements GivingPhrase {

	protected DivideGivingPhraseContext ctx;

	protected List<Giving> givings = new ArrayList<Giving>();

	public GivingPhraseImpl(final ProgramUnit programUnit, final DivideGivingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Giving addGiving(final DivideGivingContext ctx) {
		Giving result = (Giving) getASGElement(ctx);

		if (result == null) {
			result = new GivingImpl(programUnit, ctx);

			// call
			if (ctx.identifier() != null) {
				final Call givingCall = createCall(ctx.identifier());
				result.setGivingCall(givingCall);
			}

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
