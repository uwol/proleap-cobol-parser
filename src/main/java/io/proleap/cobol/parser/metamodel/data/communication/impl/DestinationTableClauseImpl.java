/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.DestinationTableClauseContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.communication.DestinationTableClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class DestinationTableClauseImpl extends CobolDivisionElementImpl implements DestinationTableClause {

	protected DestinationTableClauseContext ctx;

	protected List<ValueStmt> indexValueStmts = new ArrayList<ValueStmt>();

	protected IntegerLiteral occursIntegerLiteral;

	public DestinationTableClauseImpl(final ProgramUnit programUnit, final DestinationTableClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addIndexValueStmt(final ValueStmt indexValueStmt) {
		indexValueStmts.add(indexValueStmt);
	}

	@Override
	public List<ValueStmt> getIndexValueStmts() {
		return indexValueStmts;
	}

	@Override
	public IntegerLiteral getOccursIntegerLiteral() {
		return occursIntegerLiteral;
	}

	@Override
	public void setIntegerLiteral(final IntegerLiteral occursIntegerLiteral) {
		this.occursIntegerLiteral = occursIntegerLiteral;
	}

}
