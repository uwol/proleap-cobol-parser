/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.start.impl;

import io.proleap.cobol.Cobol85Parser.StartKeyContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.start.Key;

public class KeyImpl extends CobolDivisionElementImpl implements Key {

	protected Call comparisonCall;

	protected final StartKeyContext ctx;

	protected Type type;

	public KeyImpl(final ProgramUnit programUnit, final StartKeyContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getComparisonCall() {
		return comparisonCall;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setComparisonCall(final Call comparisonCall) {
		this.comparisonCall = comparisonCall;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
