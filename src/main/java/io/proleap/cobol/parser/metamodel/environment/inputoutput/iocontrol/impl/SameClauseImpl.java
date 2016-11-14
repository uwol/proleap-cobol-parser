/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.impl;

import io.proleap.cobol.Cobol85Parser.SameClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.SameClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class SameClauseImpl extends CobolDivisionElementImpl implements SameClause {

	protected final SameClauseContext ctx;

	public SameClauseImpl(final ProgramUnit programUnit, final SameClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
