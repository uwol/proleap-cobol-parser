/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionPromptOccursClauseContext;
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
