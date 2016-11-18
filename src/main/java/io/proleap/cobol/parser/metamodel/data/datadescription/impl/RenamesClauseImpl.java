/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataRenamesClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.RenamesClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class RenamesClauseImpl extends CobolDivisionElementImpl implements RenamesClause {

	protected DataRenamesClauseContext ctx;

	protected ValueStmt from;

	protected ValueStmt to;

	public RenamesClauseImpl(final ProgramUnit programUnit, final DataRenamesClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getFrom() {
		return from;
	}

	@Override
	public ValueStmt getTo() {
		return to;
	}

	@Override
	public void setFrom(final ValueStmt from) {
		this.from = from;
	}

	@Override
	public void setTo(final ValueStmt to) {
		this.to = to;
	}

}
