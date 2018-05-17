/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.continuestmt.impl;

import io.proleap.cobol.CobolParser.ContinueStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.continuestmt.ContinueStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class ContinueStatementImpl extends StatementImpl implements ContinueStatement {

	protected final ContinueStatementContext ctx;

	protected final StatementType statementType = StatementTypeEnum.CONTINUE;

	public ContinueStatementImpl(final ProgramUnit programUnit, final Scope scope, final ContinueStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

}
