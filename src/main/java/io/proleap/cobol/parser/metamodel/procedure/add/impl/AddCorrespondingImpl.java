/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.add.impl;

import io.proleap.cobol.Cobol85Parser.AddCorrespondingStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.add.AddCorresponding;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class AddCorrespondingImpl extends StatementImpl implements AddCorresponding {

	protected final AddCorrespondingStatementContext ctx;

	public AddCorrespondingImpl(final ProgramUnit programUnit, final AddCorrespondingStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
