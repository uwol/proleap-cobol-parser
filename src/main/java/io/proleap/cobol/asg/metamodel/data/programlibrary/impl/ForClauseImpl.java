/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary.impl;

import io.proleap.cobol.CobolParser.LibraryEntryProcedureForClauseContext;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ForClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

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
