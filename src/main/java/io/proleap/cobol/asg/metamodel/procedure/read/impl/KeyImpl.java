/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.read.impl;

import io.proleap.cobol.CobolParser.ReadKeyContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.read.Key;

public class KeyImpl extends CobolDivisionElementImpl implements Key {

	protected final ReadKeyContext ctx;

	protected Call keyCall;

	public KeyImpl(final ProgramUnit programUnit, final ReadKeyContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getKeyCall() {
		return keyCall;
	}

	@Override
	public void setKeyCall(final Call keyCall) {
		this.keyCall = keyCall;
	}

}
