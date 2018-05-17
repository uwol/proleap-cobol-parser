/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive.impl;

import io.proleap.cobol.CobolParser.ReceiveWithDataContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.receive.WithData;

public class WithDataImpl extends ScopeImpl implements WithData {

	protected final ReceiveWithDataContext ctx;

	public WithDataImpl(final ProgramUnit programUnit, final ReceiveWithDataContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
