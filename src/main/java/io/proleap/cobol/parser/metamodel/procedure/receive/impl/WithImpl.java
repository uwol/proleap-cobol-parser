/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.receive.impl;

import io.proleap.cobol.Cobol85Parser.ReceiveWithContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.receive.With;

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
