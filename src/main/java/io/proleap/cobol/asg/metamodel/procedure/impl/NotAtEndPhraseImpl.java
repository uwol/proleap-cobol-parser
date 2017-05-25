/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import io.proleap.cobol.Cobol85Parser.NotAtEndPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.NotAtEndPhrase;

public class NotAtEndPhraseImpl extends ScopeImpl implements NotAtEndPhrase {

	protected final NotAtEndPhraseContext ctx;

	public NotAtEndPhraseImpl(final ProgramUnit programUnit, final NotAtEndPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
