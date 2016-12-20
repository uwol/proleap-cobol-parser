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
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDivValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDivsValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.PowersValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class MultDivsValueStmtImpl extends ValueStmtImpl implements MultDivsValueStmt {

	protected MultDivsContext ctx;

	protected List<MultDivValueStmt> multDivs = new ArrayList<MultDivValueStmt>();

	protected PowersValueStmt powers;

	public MultDivsValueStmtImpl(final ProgramUnit programUnit, final MultDivsContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public MultDivValueStmt addMultDiv(final MultDivContext ctx) {
		MultDivValueStmt result = (MultDivValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new MultDivValueStmtImpl(programUnit, ctx);

			// type
			final MultDivValueStmt.Type type;

			if (ctx.ASTERISKCHAR() != null) {
				type = MultDivValueStmt.Type.Mult;
			} else if (ctx.SLASHCHAR() != null) {
				type = MultDivValueStmt.Type.Div;
			} else {
				type = null;
			}

			result.setType(type);

			// powers
			result.addPowers(ctx.powers());

			subValueStmts.add(result);
		}

		return result;
	}

	@Override
	public PowersValueStmt addPowers(final PowersContext ctx) {
		PowersValueStmt result = (PowersValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new PowersValueStmtImpl(programUnit, ctx);

			// type
			final PowersValueStmt.Type type;

			if (ctx.PLUSCHAR() != null) {
				type = PowersValueStmt.Type.Plus;
			} else if (ctx.MINUSCHAR() != null) {
				type = PowersValueStmt.Type.Minus;
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

			subValueStmts.add(result);
		}

		return result;
	}

	@Override
	public List<MultDivValueStmt> getMultDivs() {
		return multDivs;
	}

	@Override
	public PowersValueStmt getPowers() {
		return powers;
	}

	@Override
	public String getValue() {
		return null;
	}

}
