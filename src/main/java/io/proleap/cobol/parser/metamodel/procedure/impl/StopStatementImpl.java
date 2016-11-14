/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.impl;

import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.StopStatement;

public class StopStatementImpl extends CobolDivisionElementImpl implements StopStatement {

	protected final StopStatementContext ctx;

	public StopStatementImpl(final ProgramUnit programUnit, final StopStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
