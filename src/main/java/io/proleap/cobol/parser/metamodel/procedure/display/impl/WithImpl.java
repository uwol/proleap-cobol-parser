/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.display.impl;

import io.proleap.cobol.Cobol85Parser.DisplayWithContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.display.With;

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
