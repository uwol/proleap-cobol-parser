/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import io.proleap.cobol.CobolParser.NotOnOverflowPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.NotOnOverflowPhrase;

public class NotOnOverflowPhraseImpl extends ScopeImpl implements NotOnOverflowPhrase {

	protected final NotOnOverflowPhraseContext ctx;

	public NotOnOverflowPhraseImpl(final ProgramUnit programUnit, final NotOnOverflowPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
