/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.impl;

import io.proleap.cobol.Cobol85Parser.BasisContext;
import io.proleap.cobol.Cobol85Parser.PowerContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.BasisValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.PowerValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class PowerValueStmtImpl extends ValueStmtImpl implements PowerValueStmt {

	protected BasisValueStmt basis;

	protected PowerContext ctx;

	public PowerValueStmtImpl(final ProgramUnit programUnit, final PowerContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public BasisValueStmt addBasis(final BasisContext ctx) {
		BasisValueStmt result = (BasisValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new BasisValueStmtImpl(programUnit, ctx);

			final ValueStmt basisValueStmt = createValueStmt(ctx.arithmeticExpression(), ctx.identifier(),
					ctx.literal());
			result.setBasisValueStmt(basisValueStmt);

			subValueStmts.add(result);
		}

		return result;
	}

	@Override
	public BasisValueStmt getBasis() {
		return basis;
	}

	@Override
	public String getValue() {
		return null;
	}

}
