/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.rewrite.impl;

import io.proleap.cobol.Cobol85Parser.RewriteFromContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.rewrite.From;

public class FromImpl extends CobolDivisionElementImpl implements From {

	protected final RewriteFromContext ctx;

	protected Call fromCall;

	public FromImpl(final ProgramUnit programUnit, final RewriteFromContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getFromCall() {
		return fromCall;
	}

	@Override
	public void setFromCall(final Call fromCall) {
		this.fromCall = fromCall;
	}

}
