/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.BasisContext;
import io.proleap.cobol.Cobol85Parser.PowerContext;
import io.proleap.cobol.Cobol85Parser.PowersContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.type.CobolBaseType;
import io.proleap.cobol.asg.metamodel.type.Type;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.Basis;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.Power;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.Powers;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class PowersImpl extends ValueStmtImpl implements Powers {

	protected Basis basis;

	protected PowersContext ctx;

	protected List<Power> powers = new ArrayList<Power>();

	protected PowersType powersType;

	public PowersImpl(final ProgramUnit programUnit, final PowersContext ctx) {
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
	public Power addPower(final PowerContext ctx) {
		Power result = (Power) getASGElement(ctx);

		if (result == null) {
			result = new PowerImpl(programUnit, ctx);

			// basis
			result.addBasis(ctx.basis());

			powers.add(result);
			subValueStmts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Basis getBasis() {
		return basis;
	}

	@Override
	public List<Power> getPowers() {
		return powers;
	}

	@Override
	public PowersType getPowersType() {
		return powersType;
	}

	@Override
	public Type getType() {
		return basis.getType();
	}

	@Override
	public Object getValue() {
		final Object basisValue;

		if (powersType == null) {
			basisValue = basis.getValue();
		} else if (PowersType.MINUS.equals(powersType)) {
			if (CobolBaseType.INTEGER.equals(basis.getType())) {
				basisValue = -1 * (Integer) basis.getValue();
			} else if (CobolBaseType.FLOAT.equals(basis.getType())) {
				basisValue = -1.0 * (Double) basis.getValue();
			} else {
				basisValue = null;
			}
		} else {
			basisValue = basis.getValue();
		}

		final Object result;

		if (powers.isEmpty()) {
			result = basisValue;
		} else if (powers.size() == 1) {
			final Power power = powers.get(0);
			final Object powerValue = power.getValue();

			result = powers.get(0);
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public void setPowersType(final PowersType powersType) {
		this.powersType = powersType;
	}
}
