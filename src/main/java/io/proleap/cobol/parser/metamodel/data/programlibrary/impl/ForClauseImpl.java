/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.programlibrary.impl;

import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureForClauseContext;
import io.proleap.cobol.parser.metamodel.Literal;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.programlibrary.ForClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class ForClauseImpl extends CobolDivisionElementImpl implements ForClause {

	protected LibraryEntryProcedureForClauseContext ctx;

	protected Literal forLiteral;

	public ForClauseImpl(final ProgramUnit programUnit, final LibraryEntryProcedureForClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Literal getForLiteral() {
		return forLiteral;
	}

	@Override
	public void setForLiteral(final Literal forLiteral) {
		this.forLiteral = forLiteral;
	}

}
