/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close.impl;

import io.proleap.cobol.CobolParser.CloseRelativeStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseRelativeStatement;

public class CloseRelativeStatementImpl extends CobolDivisionElementImpl implements CloseRelativeStatement {

	protected CloseRelativeStatementContext ctx;

	protected WithType withType;

	public CloseRelativeStatementImpl(final ProgramUnit programUnit, final CloseRelativeStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public WithType getWithType() {
		return withType;
	}

	@Override
	public void setWithType(final WithType withType) {
		this.withType = withType;
	}

}
