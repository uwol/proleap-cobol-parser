/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.ReportGroupTypeClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.TypeClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class TypeClauseImpl extends CobolDivisionElementImpl implements TypeClause {

	protected ReportGroupTypeClauseContext ctx;

	protected ValueStmt dataValueStmt;

	protected Type type;

	public TypeClauseImpl(final ProgramUnit programUnit, final ReportGroupTypeClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getDataValueStmt() {
		return dataValueStmt;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setDataValueStmt(final ValueStmt dataValueStmt) {
		this.dataValueStmt = dataValueStmt;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
