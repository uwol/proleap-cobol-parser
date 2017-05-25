/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.write.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.WriteNotAtEndOfPagePhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.write.NotAtEndOfPagePhrase;

public class NotAtEndOfPagePhraseImpl extends CobolDivisionElementImpl implements NotAtEndOfPagePhrase {

	protected WriteNotAtEndOfPagePhraseContext ctx;

	protected List<Statement> statements = new ArrayList<Statement>();

	public NotAtEndOfPagePhraseImpl(final ProgramUnit programUnit, final WriteNotAtEndOfPagePhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addStatement(final Statement statement) {
		statements.add(statement);
	}

	@Override
	public List<Statement> getStatements() {
		return statements;
	}
}
