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
import io.proleap.cobol.asg.metamodel.procedure.NotAtEnd;

public class NotAtEndImpl extends ScopeImpl implements NotAtEnd {

	protected final NotAtEndPhraseContext ctx;

	public NotAtEndImpl(final ProgramUnit programUnit, final NotAtEndPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
