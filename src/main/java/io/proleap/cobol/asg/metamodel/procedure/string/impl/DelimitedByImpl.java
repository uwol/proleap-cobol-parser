/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.string.impl;

import io.proleap.cobol.Cobol85Parser.StringDelimitedByPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.string.DelimitedBy;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class DelimitedByImpl extends CobolDivisionElementImpl implements DelimitedBy {

	protected ValueStmt charactersValueStmt;

	protected final StringDelimitedByPhraseContext ctx;

	protected DelimitedByType delimitedByType;

	public DelimitedByImpl(final ProgramUnit programUnit, final StringDelimitedByPhraseContext ctx) {
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
