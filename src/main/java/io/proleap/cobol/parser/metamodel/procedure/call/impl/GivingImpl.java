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
import io.proleap.cobol.parser.metamodel.procedure.call.Giving;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class GivingImpl extends StatementImpl implements Giving {

	protected final CallGivingPhraseContext ctx;

	protected Call givingCall;

	public GivingImpl(final ProgramUnit programUnit, final CallGivingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getGivingCall() {
		return givingCall;
	}

	@Override
	public void setGivingCall(final Call givingCall) {
		this.givingCall = givingCall;
	}

}
