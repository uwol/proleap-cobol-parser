/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.SortGivingContext;
import io.proleap.cobol.CobolParser.SortGivingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.sort.Giving;
import io.proleap.cobol.asg.metamodel.procedure.sort.GivingPhrase;

public class GivingPhraseImpl extends CobolDivisionElementImpl implements GivingPhrase {

	protected final SortGivingPhraseContext ctx;

	protected List<Giving> givings = new ArrayList<Giving>();

	public GivingPhraseImpl(final ProgramUnit programUnit, final SortGivingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Giving addGiving(final SortGivingContext ctx) {
		Giving result = (Giving) getASGElement(ctx);

		if (result == null) {
			result = new GivingImpl(programUnit, ctx);

			// file call
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			// close procedure
			final Giving.CloseProcedure closeProcedure;

			if (ctx.WITH() != null && ctx.REMOVE() != null && ctx.CRUNCH() != null) {
				closeProcedure = Giving.CloseProcedure.WITH_REMOVE_CRUNCH;
			} else if (ctx.NO() != null && ctx.REWIND() != null) {
				closeProcedure = Giving.CloseProcedure.NO_REWIND;
			} else if (ctx.LOCK() != null) {
				closeProcedure = Giving.CloseProcedure.LOCK;
			} else if (ctx.SAVE() != null) {
				closeProcedure = Giving.CloseProcedure.SAVE;
			} else if (ctx.RELEASE() != null) {
				closeProcedure = Giving.CloseProcedure.RELEASE;
			} else if (ctx.CRUNCH() != null) {
				closeProcedure = Giving.CloseProcedure.CRUNCH;
			} else {
				closeProcedure = null;
			}

			result.setCloseProcedure(closeProcedure);

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
