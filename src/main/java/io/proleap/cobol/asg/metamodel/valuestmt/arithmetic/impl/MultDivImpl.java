/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.impl;

import io.proleap.cobol.Cobol85Parser.MultDivContext;
import io.proleap.cobol.Cobol85Parser.PowerContext;
import io.proleap.cobol.Cobol85Parser.PowersContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDiv;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.Powers;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class MultDivImpl extends ValueStmtImpl implements MultDiv {

	protected MultDivContext ctx;

	protected Powers powers;

	protected Type type;

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
			final Powers.Type type;

			if (ctx.PLUSCHAR() != null) {
				type = Powers.Type.PLUS;
			} else if (ctx.MINUSCHAR() != null) {
				type = Powers.Type.MINUS;
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
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Powers getPowers() {
		return powers;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
