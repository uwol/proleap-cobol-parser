/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.write.impl;

import io.proleap.cobol.CobolParser.WriteAtEndOfPagePhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.write.AtEndOfPagePhrase;

public class AtEndOfPagePhraseImpl extends ScopeImpl implements AtEndOfPagePhrase {

	protected WriteAtEndOfPagePhraseContext ctx;

	public AtEndOfPagePhraseImpl(final ProgramUnit programUnit, final WriteAtEndOfPagePhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}
}
