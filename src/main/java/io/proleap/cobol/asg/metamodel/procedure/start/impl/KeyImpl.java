/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.start.impl;

import io.proleap.cobol.CobolParser.StartKeyContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.start.Key;

public class KeyImpl extends CobolDivisionElementImpl implements Key {

	protected Call comparisonCall;

	protected final StartKeyContext ctx;

	protected KeyType keyType;

	public KeyImpl(final ProgramUnit programUnit, final StartKeyContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getComparisonCall() {
		return comparisonCall;
	}

	@Override
	public KeyType getKeyType() {
		return keyType;
	}

	@Override
	public void setComparisonCall(final Call comparisonCall) {
		this.comparisonCall = comparisonCall;
	}

	@Override
	public void setKeyType(final KeyType keyType) {
		this.keyType = keyType;
	}

}
