/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file.impl;

import io.proleap.cobol.Cobol85Parser.RecordContainsClauseContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.file.RecordContainsClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class RecordContainsClauseImpl extends CobolDivisionElementImpl implements RecordContainsClause {

	protected final RecordContainsClauseContext ctx;

	protected ValueStmt dependingOnValueStmt;

	protected IntegerLiteral from;

	protected IntegerLiteral to;

	protected boolean varying;

	public RecordContainsClauseImpl(final ProgramUnit programUnit, final RecordContainsClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getDependingOnValueStmt() {
		return dependingOnValueStmt;
	}

	@Override
	public IntegerLiteral getFrom() {
		return from;
	}

	@Override
	public IntegerLiteral getTo() {
		return to;
	}

	@Override
	public boolean isVarying() {
		return varying;
	}

	@Override
	public void setDependingOnValueStmt(final ValueStmt dependingOnValueStmt) {
		this.dependingOnValueStmt = dependingOnValueStmt;
	}

	@Override
	public void setFrom(final IntegerLiteral from) {
		this.from = from;
	}

	@Override
	public void setTo(final IntegerLiteral to) {
		this.to = to;
	}

	@Override
	public void setVarying(final boolean varying) {
		this.varying = varying;
	}

}
