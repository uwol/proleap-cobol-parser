/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.unstring.impl;

import io.proleap.cobol.Cobol85Parser.UnstringWithPointerPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.unstring.WithPointer;

public class WithPointerImpl extends CobolDivisionElementImpl implements WithPointer {

	protected final UnstringWithPointerPhraseContext ctx;

	protected Call pointerCall;

	public WithPointerImpl(final ProgramUnit programUnit, final UnstringWithPointerPhraseContext ctx) {
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
