/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.exit.impl;

import io.proleap.cobol.CobolParser.ExitStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.exit.ExitStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class ExitStatementImpl extends StatementImpl implements ExitStatement {

	protected final ExitStatementContext ctx;

	protected final StatementType statementType = StatementTypeEnum.EXIT;

	public ExitStatementImpl(final ProgramUnit programUnit, final Scope scope, final ExitStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

}
