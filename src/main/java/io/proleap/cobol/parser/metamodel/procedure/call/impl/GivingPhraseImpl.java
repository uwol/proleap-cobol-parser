/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.call.impl;

import io.proleap.cobol.Cobol85Parser.CallGivingPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.call.GivingPhrase;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class GivingPhraseImpl extends StatementImpl implements GivingPhrase {

	protected final CallGivingPhraseContext ctx;

	protected Call giving;

	public GivingPhraseImpl(final ProgramUnit programUnit, final CallGivingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getGiving() {
		return giving;
	}

	@Override
	public void setGiving(final Call giving) {
		this.giving = giving;
	}

}
