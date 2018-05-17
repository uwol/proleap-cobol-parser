/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.returnstmt.impl;

import io.proleap.cobol.CobolParser.ReturnIntoContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.returnstmt.Into;

public class IntoImpl extends CobolDivisionElementImpl implements Into {

	protected final ReturnIntoContext ctx;

	protected Call intoCall;

	public IntoImpl(final ProgramUnit programUnit, final ReturnIntoContext ctx) {
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
