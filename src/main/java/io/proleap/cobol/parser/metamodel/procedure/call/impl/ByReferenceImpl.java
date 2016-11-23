/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.call.impl;

import io.proleap.cobol.Cobol85Parser.CallByReferenceContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.call.ByReference;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class ByReferenceImpl extends StatementImpl implements ByReference {

	protected Call call;

	protected final CallByReferenceContext ctx;

	protected Type type;

	public ByReferenceImpl(final ProgramUnit programUnit, final CallByReferenceContext ctx) {
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
