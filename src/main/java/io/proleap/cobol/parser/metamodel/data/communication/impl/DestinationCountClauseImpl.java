/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication.impl;

import io.proleap.cobol.Cobol85Parser.DestinationCountClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.communication.DestinationCountClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class DestinationCountClauseImpl extends CobolDivisionElementImpl implements DestinationCountClause {

	protected DestinationCountClauseContext ctx;

	protected ValueStmt dataDescValueStmt;

	public DestinationCountClauseImpl(final ProgramUnit programUnit, final DestinationCountClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getDataDescValueStmt() {
		return dataDescValueStmt;
	}

	@Override
	public void setDataDescValueStmt(final ValueStmt dataDescValueStmt) {
		this.dataDescValueStmt = dataDescValueStmt;
	}

}
