/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.display.impl;

import io.proleap.cobol.CobolParser.DisplayWithContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.display.With;

public class WithImpl extends CobolDivisionElementImpl implements With {

	protected DisplayWithContext ctx;

	protected boolean noAdvancing;

	public WithImpl(final ProgramUnit programUnit, final DisplayWithContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isNoAdvancing() {
		return noAdvancing;
	}

	@Override
	public void setNoAdvancing(final boolean noAdvancing) {
		this.noAdvancing = noAdvancing;
	}
}
