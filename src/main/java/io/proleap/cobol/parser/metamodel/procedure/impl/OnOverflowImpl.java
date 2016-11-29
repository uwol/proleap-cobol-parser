/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.impl;

import io.proleap.cobol.Cobol85Parser.OnOverflowPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.StatementsContainerImpl;
import io.proleap.cobol.parser.metamodel.procedure.OnOverflow;

public class OnOverflowImpl extends StatementsContainerImpl implements OnOverflow {

	protected final OnOverflowPhraseContext ctx;

	public OnOverflowImpl(final ProgramUnit programUnit, final OnOverflowPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
