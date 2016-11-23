/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.add.impl;

import io.proleap.cobol.Cobol85Parser.AddFromContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.add.From;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class FromImpl extends CobolDivisionElementImpl implements From {

	protected final AddFromContext ctx;

	protected ValueStmt from;

	public FromImpl(final ProgramUnit programUnit, final AddFromContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getFrom() {
		return from;
	}

	@Override
	public void setFrom(final ValueStmt from) {
		this.from = from;
	}

}
