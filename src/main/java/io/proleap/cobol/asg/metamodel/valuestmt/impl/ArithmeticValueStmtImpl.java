/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.impl;

import io.proleap.cobol.Cobol85Parser.ArithmeticExpressionContext;
import io.proleap.cobol.Cobol85Parser.MultDivContext;
import io.proleap.cobol.Cobol85Parser.MultDivsContext;
import io.proleap.cobol.Cobol85Parser.PlusMinusContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDivs;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.PlusMinus;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.impl.MultDivsImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.impl.PlusMinusImpl;

public class ArithmeticValueStmtImpl extends ValueStmtImpl implements ArithmeticValueStmt {

	protected ArithmeticExpressionContext ctx;

	public ArithmeticValueStmtImpl(final ProgramUnit programUnit, final ArithmeticExpressionContext ctx) {
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

			subValueStmts.add(result);
		}

		return result;
	}

	@Override
	public PlusMinus addPlusMinus(final PlusMinusContext ctx) {
		PlusMinus result = (PlusMinus) getASGElement(ctx);

		if (result == null) {
			result = new PlusMinusImpl(programUnit, ctx);

			// type
			final PlusMinus.Type type;

			if (ctx.PLUSCHAR() != null) {
				type = PlusMinus.Type.Plus;
			} else if (ctx.MINUSCHAR() != null) {
				type = PlusMinus.Type.Minus;
			} else {
				type = null;
			}

			result.setType(type);

			// mult divs
			result.addMultDivs(ctx.multDivs());

			subValueStmts.add(result);
		}

		return result;
	}

	@Override
	public String getValue() {
		return null;
	}

}
