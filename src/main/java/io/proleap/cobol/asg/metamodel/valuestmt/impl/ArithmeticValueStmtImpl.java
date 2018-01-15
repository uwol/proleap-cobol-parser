/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import io.proleap.cobol.asg.util.CastUtils;

public class ArithmeticValueStmtImpl extends ValueStmtImpl implements ArithmeticValueStmt {

	protected ArithmeticExpressionContext ctx;

	protected MultDivs multDivs;

	protected List<PlusMinus> plusMinus = new ArrayList<PlusMinus>();

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

			multDivs = result;
			subValueStmts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PlusMinus addPlusMinus(final PlusMinusContext ctx) {
		PlusMinus result = (PlusMinus) getASGElement(ctx);

		if (result == null) {
			result = new PlusMinusImpl(programUnit, ctx);

			// type
			final PlusMinus.PlusMinusType type;

			if (ctx.PLUSCHAR() != null) {
				type = PlusMinus.PlusMinusType.PLUS;
			} else if (ctx.MINUSCHAR() != null) {
				type = PlusMinus.PlusMinusType.MINUS;
			} else {
				type = null;
			}

			result.setPlusMinusType(type);

			// mult divs
			result.addMultDivs(ctx.multDivs());

			plusMinus.add(result);
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
	public List<PlusMinus> getPlusMinus() {
		return plusMinus;
	}

	@Override
	public Object getValue() {
		final Object result;

		if (plusMinus.isEmpty()) {
			result = multDivs.getValue();
		} else {
			BigDecimal numberValue = CastUtils.castBigDecimal(multDivs.getValue());

			if (numberValue == null) {
				result = null;
			} else {
				for (final PlusMinus plusMinusEntry : plusMinus) {
					final BigDecimal plusMinusEntryValue = CastUtils.castBigDecimal(plusMinusEntry.getValue());

					if (plusMinusEntryValue == null) {
						numberValue = null;
						break;
					} else {
						numberValue = numberValue.add(plusMinusEntryValue);
					}
				}

				result = numberValue;
			}
		}

		return result;
	}
}
