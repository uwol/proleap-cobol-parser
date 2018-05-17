/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import io.proleap.cobol.CobolParser.PerformFromContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.FromPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class FromPhraseImpl extends CobolDivisionElementImpl implements FromPhrase {

	protected final PerformFromContext ctx;

	protected ValueStmt fromValueStmt;

	public FromPhraseImpl(final ProgramUnit programUnit, final PerformFromContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getFromValueStmt() {
		return fromValueStmt;
	}

	@Override
	public void setFromValueStmt(final ValueStmt fromValueStmt) {
		this.fromValueStmt = fromValueStmt;
	}

}
