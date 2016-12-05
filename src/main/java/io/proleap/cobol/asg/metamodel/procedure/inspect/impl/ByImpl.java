/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect.impl;

import io.proleap.cobol.Cobol85Parser.InspectByContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.inspect.By;

public class ByImpl extends InspectPhraseImpl implements By {

	protected Call by;

	protected final InspectByContext ctx;

	public ByImpl(final ProgramUnit programUnit, final InspectByContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getByCall() {
		return by;
	}

	@Override
	public void setByCall(final Call by) {
		this.by = by;
	}

}
