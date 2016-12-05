/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.write.impl;

import io.proleap.cobol.Cobol85Parser.WriteFromPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.write.From;

public class FromImpl extends CobolDivisionElementImpl implements From {

	protected WriteFromPhraseContext ctx;

	protected Call fromCall;

	public FromImpl(final ProgramUnit programUnit, final WriteFromPhraseContext ctx) {
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
