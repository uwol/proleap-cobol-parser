/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.programlibrary.impl;

import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureGivingClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.programlibrary.GivingClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class GivingClauseImpl extends CobolDivisionElementImpl implements GivingClause {

	protected LibraryEntryProcedureGivingClauseContext ctx;

	protected ValueStmt givingValueStmt;

	public GivingClauseImpl(final ProgramUnit programUnit, final LibraryEntryProcedureGivingClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getGivingValueStmt() {
		return givingValueStmt;
	}

	@Override
	public void setGivingValueStmt(final ValueStmt givingValueStmt) {
		this.givingValueStmt = givingValueStmt;
	}

}
