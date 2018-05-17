/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.string.impl;

import io.proleap.cobol.CobolParser.StringIntoPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.string.IntoPhrase;

public class IntoPhraseImpl extends CobolDivisionElementImpl implements IntoPhrase {

	protected final StringIntoPhraseContext ctx;

	protected Call intoCall;

	public IntoPhraseImpl(final ProgramUnit programUnit, final StringIntoPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getIntoCall() {
		return intoCall;
	}

	@Override
	public void setIntoCall(final Call intoCall) {
		this.intoCall = intoCall;
	}

}
