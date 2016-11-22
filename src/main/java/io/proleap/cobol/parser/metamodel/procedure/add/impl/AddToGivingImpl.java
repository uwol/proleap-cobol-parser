/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.add.impl;

import io.proleap.cobol.Cobol85Parser.AddToGivingStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.add.AddToGiving;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class AddToGivingImpl extends StatementImpl implements AddToGiving {

	protected final AddToGivingStatementContext ctx;

	public AddToGivingImpl(final ProgramUnit programUnit, final AddToGivingStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
