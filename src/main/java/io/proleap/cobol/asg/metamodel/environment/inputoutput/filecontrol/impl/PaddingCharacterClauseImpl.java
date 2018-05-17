/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.CobolParser.PaddingCharacterClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.PaddingCharacterClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class PaddingCharacterClauseImpl extends CobolDivisionElementImpl implements PaddingCharacterClause {

	protected final PaddingCharacterClauseContext ctx;

	protected ValueStmt valueStmt;

	public PaddingCharacterClauseImpl(final ProgramUnit programUnit, final PaddingCharacterClauseContext ctx) {
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
