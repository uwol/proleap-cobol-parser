/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file.impl;

import io.proleap.cobol.Cobol85Parser.LinageClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.file.LinageClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class LinageClauseImpl extends CobolDivisionElementImpl implements LinageClause {

	protected final LinageClauseContext ctx;

	protected ValueStmt footingAtValueStmt;

	protected ValueStmt linesAtBottomValueStmt;

	protected ValueStmt linesAtTopValueStmt;

	protected ValueStmt numberOfLinesValueStmt;

	public LinageClauseImpl(final ProgramUnit programUnit, final LinageClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getFootingAtValueStmt() {
		return footingAtValueStmt;
	}

	@Override
	public ValueStmt getLinesAtBottomValueStmt() {
		return linesAtBottomValueStmt;
	}

	@Override
	public ValueStmt getLinesAtTopValueStmt() {
		return linesAtTopValueStmt;
	}

	@Override
	public ValueStmt getNumberOfLinesValueStmt() {
		return numberOfLinesValueStmt;
	}

	@Override
	public void setFootingAtValueStmt(final ValueStmt footingAtValueStmt) {
		this.footingAtValueStmt = footingAtValueStmt;
	}

	@Override
	public void setLinesAtBottomValueStmt(final ValueStmt linesAtBottomValueStmt) {
		this.linesAtBottomValueStmt = linesAtBottomValueStmt;
	}

	@Override
	public void setLinesAtTopValueStmt(final ValueStmt linesAtTopValueStmt) {
		this.linesAtTopValueStmt = linesAtTopValueStmt;
	}

	@Override
	public void setNumberOfLinesValueStmt(final ValueStmt numberOfLinesValueStmt) {
		this.numberOfLinesValueStmt = numberOfLinesValueStmt;
	}

}
