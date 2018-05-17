/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring.impl;

import io.proleap.cobol.CobolParser.UnstringDelimitedByPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.unstring.DelimitedByPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class DelimitedByPhraseImpl extends CobolDivisionElementImpl implements DelimitedByPhrase {

	protected final UnstringDelimitedByPhraseContext ctx;

	protected ValueStmt delimitedByValueStmt;

	public DelimitedByPhraseImpl(final ProgramUnit programUnit, final UnstringDelimitedByPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getDelimitedByValueStmt() {
		return delimitedByValueStmt;
	}

	@Override
	public void setDelimitedByValueStmt(final ValueStmt delimitedByValueStmt) {
		this.delimitedByValueStmt = delimitedByValueStmt;
	}

}
