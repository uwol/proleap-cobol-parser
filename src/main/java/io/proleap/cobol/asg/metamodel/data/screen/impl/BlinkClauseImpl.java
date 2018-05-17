/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionBlinkClauseContext;
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
