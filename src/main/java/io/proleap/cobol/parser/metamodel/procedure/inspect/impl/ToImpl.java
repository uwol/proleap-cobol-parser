/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.inspect.impl;

import io.proleap.cobol.Cobol85Parser.InspectToContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.inspect.To;

public class ToImpl extends InspectPhraseImpl implements To {

	protected final InspectToContext ctx;

	protected Call to;

	public ToImpl(final ProgramUnit programUnit, final InspectToContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getToCall() {
		return to;
	}

	@Override
	public void setToCall(final Call to) {
		this.to = to;
	}

}
