/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.display.impl;

import io.proleap.cobol.Cobol85Parser.DisplayStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class DisplayStatementImpl extends StatementImpl implements DisplayStatement {

	protected final DisplayStatementContext ctx;

	public DisplayStatementImpl(final ProgramUnit programUnit, final DisplayStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
