/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.alter.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.AlterProceedToContext;
import io.proleap.cobol.CobolParser.AlterStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.alter.AlterStatement;
import io.proleap.cobol.asg.metamodel.procedure.alter.ProceedTo;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class AlterStatementImpl extends StatementImpl implements AlterStatement {

	protected final AlterStatementContext ctx;

	protected List<ProceedTo> proceedTos = new ArrayList<ProceedTo>();

	protected final StatementType statementType = StatementTypeEnum.ALTER;

	public AlterStatementImpl(final ProgramUnit programUnit, final Scope scope, final AlterStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public ProceedTo addProceedTo(final AlterProceedToContext ctx) {
		ProceedTo result = (ProceedTo) getASGElement(ctx);

		if (result == null) {
			result = new ProceedToImpl(programUnit, ctx);

			/*
			 * source
			 */
			final Call sourceCall = createCall(ctx.procedureName(0));
			result.setSourceCall(sourceCall);

			/*
			 * target
			 */
			final Call targetCall = createCall(ctx.procedureName(1));
			result.setTargetCall(targetCall);

			proceedTos.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<ProceedTo> getProceedTos() {
		return proceedTos;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

}
