/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close.impl;

import io.proleap.cobol.CobolParser.CloseReelUnitStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseReelUnitStatement;

public class CloseReelUnitStatementImpl extends CobolDivisionElementImpl implements CloseReelUnitStatement {

	protected CloseReelUnitType closeReelUnitType;

	protected CloseReelUnitStatementContext ctx;

	protected boolean forRemoval;

	protected WithType withType;

	public CloseReelUnitStatementImpl(final ProgramUnit programUnit, final CloseReelUnitStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public CloseReelUnitType getCloseReelUnitType() {
		return closeReelUnitType;
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
	public void setCloseReelUnitType(final CloseReelUnitType closeReelUnitType) {
		this.closeReelUnitType = closeReelUnitType;
	}

	@Override
	public void setForRemoval(final boolean forRemoval) {
		this.forRemoval = forRemoval;
	}

	@Override
	public void setWithType(final WithType withType) {
		this.withType = withType;
	}

}
