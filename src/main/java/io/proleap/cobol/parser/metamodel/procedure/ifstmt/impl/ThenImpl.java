/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.ifstmt.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.IfThenContext;
import io.proleap.cobol.Cobol85Parser.StatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.Statement;
import io.proleap.cobol.parser.metamodel.procedure.ifstmt.Then;

public class ThenImpl extends CobolDivisionElementImpl implements Then {

	protected final IfThenContext ctx;

	protected boolean nextSentence;

	protected List<Statement> statements = new ArrayList<Statement>();

	public ThenImpl(final ProgramUnit programUnit, final IfThenContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Statement addStatement(final StatementContext ctx) {
		// FIXME
		return null;
	}

	@Override
	public List<Statement> getStatements() {
		return statements;
	}

	@Override
	public boolean isNextSentence() {
		return nextSentence;
	}

	@Override
	public void setNextSentence(final boolean nextSentence) {
		this.nextSentence = nextSentence;
	}

}
