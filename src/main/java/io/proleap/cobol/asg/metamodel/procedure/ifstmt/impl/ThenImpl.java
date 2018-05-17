/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.ifstmt.impl;

import io.proleap.cobol.CobolParser.IfThenContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.ifstmt.Then;

public class ThenImpl extends ScopeImpl implements Then {

	protected final IfThenContext ctx;

	protected boolean nextSentence;

	public ThenImpl(final ProgramUnit programUnit, final IfThenContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isNextSentence() {
		return nextSentence;
	}

	@Override
	public void setNextSentence(final boolean nextSentence) {
		this.nextSentence = nextSentence;
	}

}
