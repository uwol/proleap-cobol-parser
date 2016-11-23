/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.add.impl;

import io.proleap.cobol.Cobol85Parser.AddToContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.add.To;

public class ToImpl extends CobolDivisionElementImpl implements To {

	protected final AddToContext ctx;

	protected Boolean rounded;

	protected Call to;

	public ToImpl(final ProgramUnit programUnit, final AddToContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getTo() {
		return to;
	}

	@Override
	public Boolean isRounded() {
		return rounded;
	}

	@Override
	public void setRounded(final Boolean rounded) {
		this.rounded = rounded;
	}

	@Override
	public void setTo(final Call to) {
		this.to = to;
	}

}
