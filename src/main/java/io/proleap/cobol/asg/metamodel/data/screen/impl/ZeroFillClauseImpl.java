/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionZeroFillClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.ZeroFillClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ZeroFillClauseImpl extends CobolDivisionElementImpl implements ZeroFillClause {

	protected ScreenDescriptionZeroFillClauseContext ctx;

	protected boolean zeroFill;

	public ZeroFillClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionZeroFillClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isZeroFill() {
		return zeroFill;
	}

	@Override
	public void setZeroFill(final boolean zeroFill) {
		this.zeroFill = zeroFill;
	}

}
