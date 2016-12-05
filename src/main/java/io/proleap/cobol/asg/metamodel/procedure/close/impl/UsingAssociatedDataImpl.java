/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close.impl;

import io.proleap.cobol.Cobol85Parser.ClosePortFileIOUsingAssociatedDataContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.close.UsingAssociatedData;

public class UsingAssociatedDataImpl extends CobolDivisionElementImpl implements UsingAssociatedData {

	protected ClosePortFileIOUsingAssociatedDataContext ctx;

	protected Call dataCall;

	public UsingAssociatedDataImpl(final ProgramUnit programUnit, final ClosePortFileIOUsingAssociatedDataContext ctx) {
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
