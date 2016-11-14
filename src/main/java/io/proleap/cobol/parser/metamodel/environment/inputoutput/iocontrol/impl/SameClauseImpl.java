/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.SameClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.SameClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class SameClauseImpl extends CobolDivisionElementImpl implements SameClause {

	protected final SameClauseContext ctx;

	protected List<ValueStmt> fileNameValueStmts = new ArrayList<ValueStmt>();

	protected Form form;

	public SameClauseImpl(final ProgramUnit programUnit, final SameClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addFileNameValueStmt(final ValueStmt fileNameValueStmt) {
		fileNameValueStmts.add(fileNameValueStmt);
	}

	@Override
	public List<ValueStmt> getFileNameValueStmts() {
		return fileNameValueStmts;
	}

	@Override
	public Form getForm() {
		return form;
	}

	@Override
	public void setForm(final Form form) {
		this.form = form;
	}

}
