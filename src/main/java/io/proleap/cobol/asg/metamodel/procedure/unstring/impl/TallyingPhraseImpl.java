/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring.impl;

import io.proleap.cobol.Cobol85Parser.UnstringTallyingPhraseContext;
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
