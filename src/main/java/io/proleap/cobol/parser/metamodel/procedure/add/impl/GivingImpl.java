/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.add.impl;

import io.proleap.cobol.Cobol85Parser.AddGivingContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.add.Giving;

public class GivingImpl extends CobolDivisionElementImpl implements Giving {

	protected final AddGivingContext ctx;

	protected Call giving;

	protected boolean rounded;

	public GivingImpl(final ProgramUnit programUnit, final AddGivingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getGiving() {
		return giving;
	}

	@Override
	public boolean isRounded() {
		return rounded;
	}

	@Override
	public void setGiving(final Call giving) {
		this.giving = giving;
	}

	@Override
	public void setRounded(final boolean rounded) {
		this.rounded = rounded;
	}

}
