/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.send.impl;

import io.proleap.cobol.CobolParser.SendAdvancingMnemonicContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.send.AdvancingMnemonic;

public class AdvancingMnemonicImpl extends CobolDivisionElementImpl implements AdvancingMnemonic {

	protected SendAdvancingMnemonicContext ctx;

	protected Call mnemonicCall;

	public AdvancingMnemonicImpl(final ProgramUnit programUnit, final SendAdvancingMnemonicContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getMnemonicCall() {
		return mnemonicCall;
	}

	@Override
	public void setMnemonicCall(final Call mnemonicCall) {
		this.mnemonicCall = mnemonicCall;
	}

}
