/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import io.proleap.cobol.Cobol85Parser.AtEndPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.AtEnd;

public class AtEndImpl extends ScopeImpl implements AtEnd {

	protected final AtEndPhraseContext ctx;

	public AtEndImpl(final ProgramUnit programUnit, final AtEndPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
