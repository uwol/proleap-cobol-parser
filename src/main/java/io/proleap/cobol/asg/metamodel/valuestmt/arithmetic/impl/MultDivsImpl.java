/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.MultDivContext;
import io.proleap.cobol.Cobol85Parser.MultDivsContext;
import io.proleap.cobol.Cobol85Parser.PowerContext;
import io.proleap.cobol.Cobol85Parser.PowersContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDiv;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDivs;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.Powers;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class MultDivsImpl extends ValueStmtImpl implements MultDivs {

	protected MultDivsContext ctx;

	protected List<MultDiv> multDivs = new ArrayList<MultDiv>();

	protected Powers powers;

	public MultDivsImpl(final ProgramUnit programUnit, final MultDivsContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public MultDiv addMultDiv(final MultDivContext ctx) {
		MultDiv result = (MultDiv) getASGElement(ctx);

		if (result == null) {
			result = new MultDivImpl(programUnit, ctx);

			// type
			final MultDiv.Type type;

			if (ctx.ASTERISKCHAR() != null) {
				type = MultDiv.Type.Mult;
			} else if (ctx.SLASHCHAR() != null) {
				type = MultDiv.Type.Div;
			} else {
				type = null;
			}

			result.setType(type);

			// powers
			result.addPowers(ctx.powers());

			multDivs.add(result);
			subValueStmts.add(result);
		}

		return result;
	}

	@Override
	public Powers addPowers(final PowersContext ctx) {
		Powers result = (Powers) getASGElement(ctx);

		if (result == null) {
			result = new PowersImpl(programUnit, ctx);

			// type
			final Powers.Type type;

			if (ctx.PLUSCHAR() != null) {
				type = Powers.Type.Plus;
			} else if (ctx.MINUSCHAR() != null) {
				type = Powers.Type.Minus;
			} else {
				type = null;
			}

			result.setType(type);

			// basis
			result.addBasis(ctx.basis());

			// power
			for (final PowerContext powerContext : ctx.power()) {
				result.addPower(powerContext);
			}

			powers = result;
			subValueStmts.add(result);
		}

		return result;
	}

	@Override
	public List<MultDiv> getMultDivs() {
		return multDivs;
	}

	@Override
	public Powers getPowers() {
		return powers;
	}

	@Override
	public String getValue() {
		return null;
	}

}
