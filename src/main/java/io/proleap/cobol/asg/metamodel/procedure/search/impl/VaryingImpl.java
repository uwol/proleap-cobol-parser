/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.search.impl;

import io.proleap.cobol.CobolParser.SearchVaryingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.search.VaryingPhrase;

public class VaryingImpl extends CobolDivisionElementImpl implements VaryingPhrase {

	protected final SearchVaryingContext ctx;

	protected Call dataCall;

	public VaryingImpl(final ProgramUnit programUnit, final SearchVaryingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDataCall() {
		return dataCall;
	}

	@Override
	public void setDataCall(final Call dataCall) {
		this.dataCall = dataCall;
	}

}
