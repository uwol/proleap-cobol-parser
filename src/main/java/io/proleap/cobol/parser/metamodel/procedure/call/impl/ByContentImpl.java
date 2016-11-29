/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.call.impl;

import io.proleap.cobol.Cobol85Parser.CallByContentContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.call.ByContent;

public class ByContentImpl extends CobolDivisionElementImpl implements ByContent {

	protected Call call;

	protected final CallByContentContext ctx;

	protected Type type;

	public ByContentImpl(final ProgramUnit programUnit, final CallByContentContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getCall() {
		return call;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setCall(final Call call) {
		this.call = call;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
