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

import io.proleap.cobol.CobolParser.DivideIntoContext;
import io.proleap.cobol.CobolParser.DivideIntoStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideIntoStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.Into;

public class DivideIntoStatementImpl extends CobolDivisionElementImpl implements DivideIntoStatement {

	protected DivideIntoStatementContext ctx;

	protected List<Into> intos = new ArrayList<Into>();

	public DivideIntoStatementImpl(final ProgramUnit programUnit, final DivideIntoStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Into addInto(final DivideIntoContext ctx) {
		Into result = (Into) getASGElement(ctx);

		if (result == null) {
			result = new IntoImpl(programUnit, ctx);

			// call
			if (ctx.identifier() != null) {
				final Call givingCall = createCall(ctx.identifier());
				result.setGivingCall(givingCall);
			}

			// rounded
			if (ctx.ROUNDED() != null) {
				result.setRounded(true);
			}

			intos.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Into> getIntos() {
		return intos;
	}
}
