/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.goback.impl;

import io.proleap.cobol.Cobol85Parser.GobackStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.goback.GobackStatement;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class GobackStatementImpl extends StatementImpl implements GobackStatement {

	protected final GobackStatementContext ctx;

	public GobackStatementImpl(final ProgramUnit programUnit, final GobackStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
