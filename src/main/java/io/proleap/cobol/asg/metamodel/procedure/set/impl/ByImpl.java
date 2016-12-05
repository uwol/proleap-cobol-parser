/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.set.impl;

import io.proleap.cobol.Cobol85Parser.SetByValueContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.set.By;

public class ByImpl extends CobolDivisionElementImpl implements By {

	protected Call byCall;

	protected SetByValueContext ctx;

	public ByImpl(final ProgramUnit programUnit, final SetByValueContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getByCall() {
		return byCall;
	}

	@Override
	public void setByCall(final Call byCall) {
		this.byCall = byCall;
	}

}
