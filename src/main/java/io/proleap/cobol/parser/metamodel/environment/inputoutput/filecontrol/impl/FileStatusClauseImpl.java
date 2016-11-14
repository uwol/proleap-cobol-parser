/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.Cobol85Parser.FileStatusClauseContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.FileStatusClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class FileStatusClauseImpl extends CobolDivisionElementImpl implements FileStatusClause {

	protected final FileStatusClauseContext ctx;

	protected ValueStmt valueStmt;

	protected ValueStmt valueStmt2;

	public FileStatusClauseImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final FileStatusClauseContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getValueStmt() {
		return valueStmt;
	}

	@Override
	public ValueStmt getValueStmt2() {
		return valueStmt2;
	}

	@Override
	public void setValueStmt(final ValueStmt valueStmt) {
		this.valueStmt = valueStmt;
	}

	@Override
	public void setValueStmt2(final ValueStmt valueStmt) {
		valueStmt2 = valueStmt;
	}

}
