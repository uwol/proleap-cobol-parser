/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import io.proleap.cobol.Cobol85Parser.NotOnSizeErrorPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;

public class NotOnSizeErrorPhraseImpl extends ScopeImpl implements NotOnSizeErrorPhrase {

	protected final NotOnSizeErrorPhraseContext ctx;

	public NotOnSizeErrorPhraseImpl(final ProgramUnit programUnit, final NotOnSizeErrorPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
