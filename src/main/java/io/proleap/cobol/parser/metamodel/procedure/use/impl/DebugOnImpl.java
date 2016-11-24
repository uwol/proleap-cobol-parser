/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.use.impl;

import io.proleap.cobol.Cobol85Parser.UseDebugOnContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.use.DebugOn;

public class DebugOnImpl extends CobolDivisionElementImpl implements DebugOn {

	protected UseDebugOnContext ctx;

	protected Call onCall;

	protected Type type;

	public DebugOnImpl(final ProgramUnit programUnit, final UseDebugOnContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getOnCall() {
		return onCall;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setOnCall(final Call onCall) {
		this.onCall = onCall;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
