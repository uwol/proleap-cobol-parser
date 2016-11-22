/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.add.impl;

import io.proleap.cobol.Cobol85Parser.AddToContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.add.To;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class ToImpl extends CobolDivisionElementImpl implements To {

	protected final AddToContext ctx;

	protected Boolean rounded;

	protected ValueStmt toValueStmt;

	public ToImpl(final ProgramUnit programUnit, final AddToContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getToValueStmt() {
		return toValueStmt;
	}

	@Override
	public Boolean isRounded() {
		return rounded;
	}

	@Override
	public void setRounded(final Boolean rounded) {
		this.rounded = rounded;
	}

	@Override
	public void setToValueStmt(final ValueStmt toValueStmt) {
		this.toValueStmt = toValueStmt;
	}

}
