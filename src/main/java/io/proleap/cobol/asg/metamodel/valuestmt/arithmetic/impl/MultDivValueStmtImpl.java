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
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDivValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.PowersValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class MultDivValueStmtImpl extends ValueStmtImpl implements MultDivValueStmt {

	protected MultDivContext ctx;

	protected PowersValueStmt powers;

	protected Type type;

	public MultDivValueStmtImpl(final ProgramUnit programUnit, final MultDivContext ctx) {
		super(programUnit, ctx);
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
	public PowersValueStmt getPowers() {
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
