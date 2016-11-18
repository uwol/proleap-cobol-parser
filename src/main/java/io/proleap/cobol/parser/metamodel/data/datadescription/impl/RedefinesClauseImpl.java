/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataRedefinesClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.RedefinesClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class RedefinesClauseImpl extends CobolDivisionElementImpl implements RedefinesClause {

	protected DataRedefinesClauseContext ctx;

	protected ValueStmt redefinesValueStmt;

	public RedefinesClauseImpl(final ProgramUnit programUnit, final DataRedefinesClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getRedefinesValueStmt() {
		return redefinesValueStmt;
	}

	@Override
	public void setRedefinesValueStmt(final ValueStmt redefinesValueStmt) {
		this.redefinesValueStmt = redefinesValueStmt;
	}

}
