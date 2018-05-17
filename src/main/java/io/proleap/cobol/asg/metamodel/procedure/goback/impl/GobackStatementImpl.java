/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.goback.impl;

import io.proleap.cobol.CobolParser.GobackStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.goback.GobackStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class GobackStatementImpl extends StatementImpl implements GobackStatement {

	protected final GobackStatementContext ctx;

	protected final StatementType statementType = StatementTypeEnum.GO_BACK;

	public GobackStatementImpl(final ProgramUnit programUnit, final Scope scope, final GobackStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

}
