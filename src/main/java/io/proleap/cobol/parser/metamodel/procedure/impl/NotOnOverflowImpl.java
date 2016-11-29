/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.impl;

import io.proleap.cobol.Cobol85Parser.NotOnOverflowPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.ScopeImpl;
import io.proleap.cobol.parser.metamodel.procedure.NotOnOverflow;

public class NotOnOverflowImpl extends ScopeImpl implements NotOnOverflow {

	protected final NotOnOverflowPhraseContext ctx;

	public NotOnOverflowImpl(final ProgramUnit programUnit, final NotOnOverflowPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
