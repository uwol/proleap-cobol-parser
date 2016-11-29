/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.receive.impl;

import io.proleap.cobol.Cobol85Parser.ReceiveNoDataContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.ScopeImpl;
import io.proleap.cobol.parser.metamodel.procedure.receive.NoData;

public class NoDataImpl extends ScopeImpl implements NoData {

	protected final ReceiveNoDataContext ctx;

	public NoDataImpl(final ProgramUnit programUnit, final ReceiveNoDataContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
