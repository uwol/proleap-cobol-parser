/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.write.impl;

import io.proleap.cobol.CobolParser.WriteNotAtEndOfPagePhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.write.NotAtEndOfPagePhrase;

public class NotAtEndOfPagePhraseImpl extends ScopeImpl implements NotAtEndOfPagePhrase {

	protected WriteNotAtEndOfPagePhraseContext ctx;

	public NotAtEndOfPagePhraseImpl(final ProgramUnit programUnit, final WriteNotAtEndOfPagePhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}
}
