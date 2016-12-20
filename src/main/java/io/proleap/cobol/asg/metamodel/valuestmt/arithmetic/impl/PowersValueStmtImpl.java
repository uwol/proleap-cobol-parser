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

import io.proleap.cobol.Cobol85Parser.BasisContext;
import io.proleap.cobol.Cobol85Parser.PowerContext;
import io.proleap.cobol.Cobol85Parser.PowersContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.BasisValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.PowerValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.PowersValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class PowersValueStmtImpl extends ValueStmtImpl implements PowersValueStmt {

	protected BasisValueStmt basis;

	protected PowersContext ctx;

	protected List<PowerValueStmt> powers = new ArrayList<PowerValueStmt>();

	protected Type type;

	public PowersValueStmtImpl(final ProgramUnit programUnit, final PowersContext ctx) {
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
	public PowerValueStmt addPower(final PowerContext ctx) {
		PowerValueStmt result = (PowerValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new PowerValueStmtImpl(programUnit, ctx);

			result.addBasis(ctx.basis());

			subValueStmts.add(result);
		}

		return result;
	}

	@Override
	public BasisValueStmt getBasis() {
		return basis;
	}

	@Override
	public List<PowerValueStmt> getPowers() {
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
