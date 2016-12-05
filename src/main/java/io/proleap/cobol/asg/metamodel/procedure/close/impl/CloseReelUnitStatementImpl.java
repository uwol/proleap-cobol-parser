/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close.impl;

import io.proleap.cobol.Cobol85Parser.CloseReelUnitStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseReelUnitStatement;

public class CloseReelUnitStatementImpl extends CobolDivisionElementImpl implements CloseReelUnitStatement {

	protected CloseReelUnitStatementContext ctx;

	protected boolean forRemoval;

	protected Type type;

	protected WithType withType;

	public CloseReelUnitStatementImpl(final ProgramUnit programUnit, final CloseReelUnitStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public WithType getWithType() {
		return withType;
	}

	@Override
	public boolean isForRemovel() {
		return forRemoval;
	}

	@Override
	public void setForRemoval(final boolean forRemoval) {
		this.forRemoval = forRemoval;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

	@Override
	public void setWithType(final WithType withType) {
		this.withType = withType;
	}

}
