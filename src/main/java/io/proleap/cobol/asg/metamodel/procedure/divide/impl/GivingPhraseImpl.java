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
import io.proleap.cobol.Cobol85Parser.DivideGivingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideGivingStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.GivingPhrase;

public class GivingPhraseImpl extends CobolDivisionElementImpl implements GivingPhrase {

	protected DivideGivingPhraseContext ctx;

	protected List<DivideGivingStatement> givings = new ArrayList<DivideGivingStatement>();

	public GivingPhraseImpl(final ProgramUnit programUnit, final DivideGivingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DivideGivingStatement addGiving(final DivideGivingContext ctx) {
		DivideGivingStatement result = (DivideGivingStatement) getASGElement(ctx);

		if (result == null) {
			result = new DivideGivingStatementImpl(programUnit, ctx);

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
	public List<DivideGivingStatement> getGivings() {
		return givings;
	}

}
