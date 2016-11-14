/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.Cobol85Parser.RecordKeyClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.PasswordClause;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.RecordKeyClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class RecordKeyClauseImpl extends CobolDivisionElementImpl implements RecordKeyClause {

	protected final RecordKeyClauseContext ctx;

	protected PasswordClause passwordClause;

	protected ValueStmt valueStmt;

	public RecordKeyClauseImpl(final ProgramUnit programUnit, final RecordKeyClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public PasswordClause getPasswordClause() {
		return passwordClause;
	}

	@Override
	public ValueStmt getValueStmt() {
		return valueStmt;
	}

	@Override
	public void setPasswordClause(final PasswordClause passwordClause) {
		this.passwordClause = passwordClause;
	}

	@Override
	public void setValueStmt(final ValueStmt valueStmt) {
		this.valueStmt = valueStmt;
	}

}
