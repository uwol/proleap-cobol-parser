/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionZeroFillClauseContext;
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
