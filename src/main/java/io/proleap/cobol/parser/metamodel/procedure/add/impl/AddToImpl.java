/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.add.impl;

import io.proleap.cobol.Cobol85Parser.AddToStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.add.AddTo;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class AddToImpl extends StatementImpl implements AddTo {

	protected final AddToStatementContext ctx;

	public AddToImpl(final ProgramUnit programUnit, final AddToStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
