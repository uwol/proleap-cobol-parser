/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.impl;

import io.proleap.cobol.CobolParser.MultDivContext;
import io.proleap.cobol.CobolParser.MultDivsContext;
import io.proleap.cobol.CobolParser.PlusMinusContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDivs;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.PlusMinus;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class PlusMinusImpl extends ValueStmtImpl implements PlusMinus {

	protected PlusMinusContext ctx;

	protected MultDivs multDivs;

	protected PlusMinusType plusMinusType;

	public PlusMinusImpl(final ProgramUnit programUnit, final PlusMinusContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public MultDivs addMultDivs(final MultDivsContext ctx) {
		MultDivs result = (MultDivs) getASGElement(ctx);

		if (result == null) {
			result = new MultDivsImpl(programUnit, ctx);

			// powers
			result.addPowers(ctx.powers());

			// mult div
			for (final MultDivContext multDivContext : ctx.multDiv()) {
				result.addMultDiv(multDivContext);
			}

			multDivs = result;
			subValueStmts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MultDivs getMultDivs() {
		return multDivs;
	}

	@Override
	public PlusMinusType getPlusMinusType() {
		return plusMinusType;
	}

	@Override
	public void setPlusMinusType(final PlusMinusType plusMinusType) {
		this.plusMinusType = plusMinusType;
	}
}
