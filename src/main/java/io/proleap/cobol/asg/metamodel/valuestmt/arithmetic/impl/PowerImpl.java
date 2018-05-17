/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.impl;

import io.proleap.cobol.CobolParser.BasisContext;
import io.proleap.cobol.CobolParser.PowerContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.Basis;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.Power;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class PowerImpl extends ValueStmtImpl implements Power {

	protected Basis basis;

	protected PowerContext ctx;

	public PowerImpl(final ProgramUnit programUnit, final PowerContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Basis addBasis(final BasisContext ctx) {
		Basis result = (Basis) getASGElement(ctx);

		if (result == null) {
			result = new BasisImpl(programUnit, ctx);

			// value stmt
			final ValueStmt basisValueStmt = createValueStmt(ctx.arithmeticExpression(), ctx.identifier(),
					ctx.literal());
			result.setBasisValueStmt(basisValueStmt);

			basis = result;
			subValueStmts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Basis getBasis() {
		return basis;
	}
}
