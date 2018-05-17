/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.impl;

import io.proleap.cobol.CobolParser.MultDivContext;
import io.proleap.cobol.CobolParser.PowerContext;
import io.proleap.cobol.CobolParser.PowersContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDiv;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.Powers;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class MultDivImpl extends ValueStmtImpl implements MultDiv {

	protected MultDivContext ctx;

	protected MultDivType multDivType;

	protected Powers powers;

	public MultDivImpl(final ProgramUnit programUnit, final MultDivContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Powers addPowers(final PowersContext ctx) {
		Powers result = (Powers) getASGElement(ctx);

		if (result == null) {
			result = new PowersImpl(programUnit, ctx);

			// type
			final Powers.PowersType type;

			if (ctx.PLUSCHAR() != null) {
				type = Powers.PowersType.PLUS;
			} else if (ctx.MINUSCHAR() != null) {
				type = Powers.PowersType.MINUS;
			} else {
				type = null;
			}

			result.setPowersType(type);

			// basis
			if (ctx.basis() != null) {
				result.addBasis(ctx.basis());
			}

			// power
			for (final PowerContext powerContext : ctx.power()) {
				result.addPower(powerContext);
			}

			powers = result;
			subValueStmts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MultDivType getMultDivType() {
		return multDivType;
	}

	@Override
	public Powers getPowers() {
		return powers;
	}

	@Override
	public void setMultDivType(final MultDivType multDivType) {
		this.multDivType = multDivType;
	}
}
