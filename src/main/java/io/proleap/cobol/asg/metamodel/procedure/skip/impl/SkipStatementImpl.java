/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.skip.impl;

import io.proleap.cobol.Cobol85Parser.SkipStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.skip.SkipStatement;

public class SkipStatementImpl extends StatementImpl implements SkipStatement {

	protected final SkipStatementContext ctx;

	protected SkipType skipType;

	protected final StatementType statementType = StatementTypeEnum.SKIP;

	public SkipStatementImpl(final ProgramUnit programUnit, final Scope scope, final SkipStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public SkipType getSkipType() {
		return skipType;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setSkipType(final SkipType skipType) {
		this.skipType = skipType;
	}
}
