/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.Cobol85Parser.RelativeKeyClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.RelativeKeyClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class RelativeKeyClauseImpl extends CobolDivisionElementImpl implements RelativeKeyClause {

	protected final RelativeKeyClauseContext ctx;

	protected ValueStmt valueStmt;

	public RelativeKeyClauseImpl(final ProgramUnit programUnit, final RelativeKeyClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getValueStmt() {
		return valueStmt;
	}

	@Override
	public void setValueStmt(final ValueStmt valueStmt) {
		this.valueStmt = valueStmt;
	}

}
