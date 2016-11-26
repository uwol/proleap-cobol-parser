/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.receive.impl;

import io.proleap.cobol.Cobol85Parser.ReceiveSizeContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.receive.Size;

public class SizeImpl extends CobolDivisionElementImpl implements Size {

	protected final ReceiveSizeContext ctx;

	protected Call sizeCall;

	public SizeImpl(final ProgramUnit programUnit, final ReceiveSizeContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getSizeCall() {
		return sizeCall;
	}

	@Override
	public void setSizeCall(final Call sizeCall) {
		this.sizeCall = sizeCall;
	}

}
