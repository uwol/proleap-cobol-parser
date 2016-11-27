/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.subtract.impl;

import io.proleap.cobol.Cobol85Parser.SubtractSubtrahendContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.subtract.Subtrahend;

public class SubtrahendImpl extends CobolDivisionElementImpl implements Subtrahend {

	protected final SubtractSubtrahendContext ctx;

	protected Call subtrahendCall;

	public SubtrahendImpl(final ProgramUnit programUnit, final SubtractSubtrahendContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getSubtrahendCall() {
		return subtrahendCall;
	}

	@Override
	public void setSubtrahendCall(final Call subtrahendCall) {
		this.subtrahendCall = subtrahendCall;
	}

}
