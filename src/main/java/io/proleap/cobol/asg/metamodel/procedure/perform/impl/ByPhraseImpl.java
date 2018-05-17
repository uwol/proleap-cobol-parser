/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import io.proleap.cobol.CobolParser.PerformByContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.ByPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ByPhraseImpl extends CobolDivisionElementImpl implements ByPhrase {

	protected ValueStmt byValueStmt;

	protected final PerformByContext ctx;

	public ByPhraseImpl(final ProgramUnit programUnit, final PerformByContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getByValueStmt() {
		return byValueStmt;
	}

	@Override
	public void setByValueStmt(final ValueStmt byValueStmt) {
		this.byValueStmt = byValueStmt;
	}

}
