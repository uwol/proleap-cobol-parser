/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.close.impl;

import io.proleap.cobol.Cobol85Parser.ClosePortFileIOUsingAssociatedDataLengthContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.close.UsingAssociatedDataLength;

public class UsingAssociatedDataLengthImpl extends CobolDivisionElementImpl implements UsingAssociatedDataLength {

	protected ClosePortFileIOUsingAssociatedDataLengthContext ctx;

	protected Call dataLengthCall;

	public UsingAssociatedDataLengthImpl(final ProgramUnit programUnit,
			final ClosePortFileIOUsingAssociatedDataLengthContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDataLengthCall() {
		return dataLengthCall;
	}

	@Override
	public void setDataLengthCall(final Call dataLengthCall) {
		this.dataLengthCall = dataLengthCall;
	}

}
