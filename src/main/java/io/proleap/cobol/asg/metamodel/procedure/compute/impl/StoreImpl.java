/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.compute.impl;

import io.proleap.cobol.CobolParser.ComputeStoreContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.compute.Store;

public class StoreImpl extends CobolDivisionElementImpl implements Store {

	protected ComputeStoreContext ctx;

	protected boolean rounded;

	protected Call storeCall;

	public StoreImpl(final ProgramUnit programUnit, final ComputeStoreContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getStoreCall() {
		return storeCall;
	}

	@Override
	public boolean isRounded() {
		return rounded;
	}

	@Override
	public void setRounded(final boolean rounded) {
		this.rounded = rounded;
	}

	@Override
	public void setStoreCall(final Call storeCall) {
		this.storeCall = storeCall;
	}

}
