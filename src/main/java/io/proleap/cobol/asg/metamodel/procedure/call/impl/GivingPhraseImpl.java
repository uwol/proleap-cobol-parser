/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call.impl;

import io.proleap.cobol.CobolParser.CallGivingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.call.GivingPhrase;

public class GivingPhraseImpl extends CobolDivisionElementImpl implements GivingPhrase {

	protected final CallGivingPhraseContext ctx;

	protected Call givingCall;

	public GivingPhraseImpl(final ProgramUnit programUnit, final CallGivingPhraseContext ctx) {
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
