/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.send.impl;

import io.proleap.cobol.CobolParser.SendWithPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.send.With;

public class WithImpl extends CobolDivisionElementImpl implements With {

	protected SendWithPhraseContext ctx;

	protected Call withCall;

	protected WithType withType;

	public WithImpl(final ProgramUnit programUnit, final SendWithPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getWithCall() {
		return withCall;
	}

	@Override
	public WithType getWithType() {
		return withType;
	}

	@Override
	public void setWithCall(final Call withCall) {
		this.withCall = withCall;
	}

	@Override
	public void setWithType(final WithType withType) {
		this.withType = withType;
	}

}
