/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.string.impl;

import io.proleap.cobol.CobolParser.StringDelimitedByPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.string.DelimitedByPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class DelimitedByPhraseImpl extends CobolDivisionElementImpl implements DelimitedByPhrase {

	protected ValueStmt charactersValueStmt;

	protected final StringDelimitedByPhraseContext ctx;

	protected DelimitedByType delimitedByType;

	public DelimitedByPhraseImpl(final ProgramUnit programUnit, final StringDelimitedByPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getCharactersValueStmt() {
		return charactersValueStmt;
	}

	@Override
	public DelimitedByType getDelimitedByType() {
		return delimitedByType;
	}

	@Override
	public void setCharactersValueStmt(final ValueStmt charactersValueStmt) {
		this.charactersValueStmt = charactersValueStmt;
	}

	@Override
	public void setType(final DelimitedByType delimitedByType) {
		this.delimitedByType = delimitedByType;
	}

}
