/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.LabelRecordsClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.file.LabelRecordsClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class LabelRecordsClauseImpl extends CobolDivisionElementImpl implements LabelRecordsClause {

	protected final LabelRecordsClauseContext ctx;

	protected List<ValueStmt> dataNameValueStmts = new ArrayList<ValueStmt>();

	protected Type type;

	public LabelRecordsClauseImpl(final ProgramUnit programUnit, final LabelRecordsClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addDataNameValueStmt(final ValueStmt dataNameValueStmt) {
		dataNameValueStmts.add(dataNameValueStmt);
	}

	@Override
	public List<ValueStmt> getDataNames() {
		return dataNameValueStmts;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
