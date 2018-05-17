/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring.impl;

import io.proleap.cobol.CobolParser.UnstringWithPointerPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.unstring.WithPointerPhrase;

public class WithPointerPhraseImpl extends CobolDivisionElementImpl implements WithPointerPhrase {

	protected final UnstringWithPointerPhraseContext ctx;

	protected Call pointerCall;

	public WithPointerPhraseImpl(final ProgramUnit programUnit, final UnstringWithPointerPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getPointerCall() {
		return pointerCall;
	}

	@Override
	public void setPointerCall(final Call pointerCall) {
		this.pointerCall = pointerCall;
	}

}
