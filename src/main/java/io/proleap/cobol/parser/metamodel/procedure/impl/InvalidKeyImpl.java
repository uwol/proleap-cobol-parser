/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.impl;

import io.proleap.cobol.Cobol85Parser.InvalidKeyPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.ScopeImpl;
import io.proleap.cobol.parser.metamodel.procedure.InvalidKey;

public class InvalidKeyImpl extends ScopeImpl implements InvalidKey {

	protected final InvalidKeyPhraseContext ctx;

	public InvalidKeyImpl(final ProgramUnit programUnit, final InvalidKeyPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
