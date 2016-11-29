/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.impl;

import io.proleap.cobol.Cobol85Parser.NotInvalidKeyPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.ScopeImpl;
import io.proleap.cobol.parser.metamodel.procedure.NotInvalidKey;

public class NotInvalidKeyImpl extends ScopeImpl implements NotInvalidKey {

	protected final NotInvalidKeyPhraseContext ctx;

	public NotInvalidKeyImpl(final ProgramUnit programUnit, final NotInvalidKeyPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
