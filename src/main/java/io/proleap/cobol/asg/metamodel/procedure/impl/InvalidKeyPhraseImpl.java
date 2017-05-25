/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import io.proleap.cobol.Cobol85Parser.InvalidKeyPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;

public class InvalidKeyPhraseImpl extends ScopeImpl implements InvalidKeyPhrase {

	protected final InvalidKeyPhraseContext ctx;

	public InvalidKeyPhraseImpl(final ProgramUnit programUnit, final InvalidKeyPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
