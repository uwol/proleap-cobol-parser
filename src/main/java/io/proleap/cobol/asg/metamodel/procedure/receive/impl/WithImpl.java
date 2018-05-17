/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive.impl;

import io.proleap.cobol.CobolParser.ReceiveWithContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.receive.With;

public class WithImpl extends CobolDivisionElementImpl implements With {

	protected final ReceiveWithContext ctx;

	protected boolean noWait;

	public WithImpl(final ProgramUnit programUnit, final ReceiveWithContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isNoWait() {
		return noWait;
	}

	@Override
	public void setNoWait(final boolean noWait) {
		this.noWait = noWait;
	}

}
