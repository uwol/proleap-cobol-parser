/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring.impl;

import io.proleap.cobol.CobolParser.UnstringTallyingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.unstring.TallyingPhrase;

public class TallyingPhraseImpl extends CobolDivisionElementImpl implements TallyingPhrase {

	protected final UnstringTallyingPhraseContext ctx;

	protected Call tallyCountDataItemCall;

	public TallyingPhraseImpl(final ProgramUnit programUnit, final UnstringTallyingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addTallyCountDataItemCall(final Call tallyCountDataItemCall) {
		this.tallyCountDataItemCall = tallyCountDataItemCall;
	}

	@Override
	public Call getTallyCountDataItemCall() {
		return tallyCountDataItemCall;
	}

}
