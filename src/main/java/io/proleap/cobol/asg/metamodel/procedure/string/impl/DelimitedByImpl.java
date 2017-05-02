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

	protected Type type;

	public DelimitedByImpl(final ProgramUnit programUnit, final StringDelimitedByPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getCharactersValueStmt() {
		return charactersValueStmt;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setCharactersValueStmt(final ValueStmt charactersValueStmt) {
		this.charactersValueStmt = charactersValueStmt;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
