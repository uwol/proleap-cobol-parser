/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.add.impl;

import io.proleap.cobol.Cobol85Parser.AddGivingContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.add.Giving;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class GivingImpl extends CobolDivisionElementImpl implements Giving {

	protected final AddGivingContext ctx;

	protected ValueStmt givingValueStmt;

	protected Boolean rounded;

	public GivingImpl(final ProgramUnit programUnit, final AddGivingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getGivingValueStmt() {
		return givingValueStmt;
	}

	@Override
	public Boolean isRounded() {
		return rounded;
	}

	@Override
	public void setGivingValueStmt(final ValueStmt givingValueStmt) {
		this.givingValueStmt = givingValueStmt;
	}

	@Override
	public void setRounded(final Boolean rounded) {
		this.rounded = rounded;
	}

}
