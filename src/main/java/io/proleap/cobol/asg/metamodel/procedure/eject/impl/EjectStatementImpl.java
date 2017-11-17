/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.eject.impl;

import io.proleap.cobol.Cobol85Parser.EjectStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.eject.EjectStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class EjectStatementImpl extends StatementImpl implements EjectStatement {

	protected final EjectStatementContext ctx;

	protected final StatementType statementType = StatementTypeEnum.EJECT;

	public EjectStatementImpl(final ProgramUnit programUnit, final Scope scope, final EjectStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}
}
