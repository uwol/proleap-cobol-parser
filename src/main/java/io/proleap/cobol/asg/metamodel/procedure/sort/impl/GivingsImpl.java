/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.SortGivingContext;
import io.proleap.cobol.Cobol85Parser.SortGivingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.sort.Giving;
import io.proleap.cobol.asg.metamodel.procedure.sort.Givings;

public class GivingsImpl extends CobolDivisionElementImpl implements Givings {

	protected final SortGivingPhraseContext ctx;

	protected List<Giving> givings = new ArrayList<Giving>();

	public GivingsImpl(final ProgramUnit programUnit, final SortGivingPhraseContext ctx) {
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
				closeProcedure = Giving.CloseProcedure.WithRemoveCrunch;
			} else if (ctx.NO() != null && ctx.REWIND() != null) {
				closeProcedure = Giving.CloseProcedure.NoRewind;
			} else if (ctx.LOCK() != null) {
				closeProcedure = Giving.CloseProcedure.Lock;
			} else if (ctx.SAVE() != null) {
				closeProcedure = Giving.CloseProcedure.Save;
			} else if (ctx.RELEASE() != null) {
				closeProcedure = Giving.CloseProcedure.Release;
			} else if (ctx.CRUNCH() != null) {
				closeProcedure = Giving.CloseProcedure.Crunch;
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
