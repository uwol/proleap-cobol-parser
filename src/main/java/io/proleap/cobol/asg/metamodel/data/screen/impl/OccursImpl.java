/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionPromptOccursClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.Occurs;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class OccursImpl extends CobolDivisionElementImpl implements Occurs {

	protected ScreenDescriptionPromptOccursClauseContext ctx;

	protected IntegerLiteral occursTimes;

	public OccursImpl(final ProgramUnit programUnit, final ScreenDescriptionPromptOccursClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getOccursTimes() {
		return occursTimes;
	}

	@Override
	public void setOccursTimes(final IntegerLiteral occursTimes) {
		this.occursTimes = occursTimes;
	}

}
