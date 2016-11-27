/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.send.impl;

import io.proleap.cobol.Cobol85Parser.SendWithPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.send.With;

public class WithImpl extends CobolDivisionElementImpl implements With {

	protected SendWithPhraseContext ctx;

	protected Type type;

	protected Call withCall;

	public WithImpl(final ProgramUnit programUnit, final SendWithPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Call getWithCall() {
		return withCall;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

	@Override
	public void setWithCall(final Call withCall) {
		this.withCall = withCall;
	}

}
