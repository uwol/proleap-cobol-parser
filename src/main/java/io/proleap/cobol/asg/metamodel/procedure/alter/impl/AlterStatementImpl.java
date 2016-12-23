/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.alter.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.AlterProceedToContext;
import io.proleap.cobol.Cobol85Parser.AlterStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.alter.AlterProceedTo;
import io.proleap.cobol.asg.metamodel.procedure.alter.AlterStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class AlterStatementImpl extends StatementImpl implements AlterStatement {

	protected List<AlterProceedTo> alterProceedTos = new ArrayList<AlterProceedTo>();

	protected final AlterStatementContext ctx;

	protected final StatementType statementType = StatementTypeEnum.ALTER;

	public AlterStatementImpl(final ProgramUnit programUnit, final Scope scope, final AlterStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public AlterProceedTo addAlterProceedTo(final AlterProceedToContext ctx) {
		AlterProceedTo result = (AlterProceedTo) getASGElement(ctx);

		if (result == null) {
			result = new AlterProceedToImpl(programUnit, ctx);

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

			alterProceedTos.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<AlterProceedTo> getAlterProceedTos() {
		return alterProceedTos;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

}
