/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive.impl;

import io.proleap.cobol.CobolParser.ReceiveNoDataContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.receive.NoData;

public class NoDataImpl extends ScopeImpl implements NoData {

	protected final ReceiveNoDataContext ctx;

	public NoDataImpl(final ProgramUnit programUnit, final ReceiveNoDataContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
