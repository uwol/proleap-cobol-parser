/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.ifstmt.impl;

import io.proleap.cobol.Cobol85Parser.IfThenContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.StatementsContainerImpl;
import io.proleap.cobol.parser.metamodel.procedure.ifstmt.Then;

public class ThenImpl extends StatementsContainerImpl implements Then {

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
