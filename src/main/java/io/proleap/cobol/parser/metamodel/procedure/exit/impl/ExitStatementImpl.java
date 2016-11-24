/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.exit.impl;

import io.proleap.cobol.Cobol85Parser.ExitStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.exit.ExitStatement;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class ExitStatementImpl extends StatementImpl implements ExitStatement {

	protected final ExitStatementContext ctx;

	public ExitStatementImpl(final ProgramUnit programUnit, final ExitStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
