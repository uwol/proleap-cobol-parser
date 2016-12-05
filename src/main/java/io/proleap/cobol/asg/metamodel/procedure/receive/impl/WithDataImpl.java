/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive.impl;

import io.proleap.cobol.Cobol85Parser.ReceiveWithDataContext;
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
