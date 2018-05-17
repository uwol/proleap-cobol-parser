/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect.impl;

import io.proleap.cobol.CobolParser.InspectToContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.inspect.To;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ToImpl extends InspectPhraseImpl implements To {

	protected final InspectToContext ctx;

	protected ValueStmt toValueStmt;

	public ToImpl(final ProgramUnit programUnit, final InspectToContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getToValueStmt() {
		return toValueStmt;
	}

	@Override
	public void setToValueStmt(final ValueStmt toValueStmt) {
		this.toValueStmt = toValueStmt;
	}

}
