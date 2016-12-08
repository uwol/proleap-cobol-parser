/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionBlinkClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.BlinkClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class BlinkClauseImpl extends CobolDivisionElementImpl implements BlinkClause {

	protected boolean blink;

	protected ScreenDescriptionBlinkClauseContext ctx;

	public BlinkClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionBlinkClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isBlink() {
		return blink;
	}

	@Override
	public void setBlink(final boolean blink) {
		this.blink = blink;
	}

}
