/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.string.impl;

import io.proleap.cobol.CobolParser.StringForPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.string.ForPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ForPhraseImpl extends CobolDivisionElementImpl implements ForPhrase {

	protected final StringForPhraseContext ctx;

	protected ValueStmt forValueStmt;

	public ForPhraseImpl(final ProgramUnit programUnit, final StringForPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getForValueStmt() {
		return forValueStmt;
	}

	@Override
	public void setForValueStmt(final ValueStmt forValueStmt) {
		this.forValueStmt = forValueStmt;
	}

}
