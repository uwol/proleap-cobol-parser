/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import io.proleap.cobol.Cobol85Parser.OnSizeErrorPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeError;

public class OnSizeErrorImpl extends ScopeImpl implements OnSizeError {

	protected final OnSizeErrorPhraseContext ctx;

	public OnSizeErrorImpl(final ProgramUnit programUnit, final OnSizeErrorPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
