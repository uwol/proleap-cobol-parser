/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.initialize.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.InitializeReplacingByContext;
import io.proleap.cobol.CobolParser.InitializeReplacingPhraseContext;
import io.proleap.cobol.CobolParser.InitializeStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.initialize.InitializeStatement;
import io.proleap.cobol.asg.metamodel.procedure.initialize.ReplacingPhrase;

public class InitializeStatementImpl extends StatementImpl implements InitializeStatement {

	protected final InitializeStatementContext ctx;

	protected List<Call> dataItemCalls = new ArrayList<Call>();

	protected ReplacingPhrase replacingPhrase;

	protected final StatementType statementType = StatementTypeEnum.INITIALIZE;

	public InitializeStatementImpl(final ProgramUnit programUnit, final Scope scope,
			final InitializeStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addDataItemCall(final Call dataItemCall) {
		dataItemCalls.add(dataItemCall);
	}

	@Override
	public ReplacingPhrase addReplacingPhrase(final InitializeReplacingPhraseContext ctx) {
		ReplacingPhrase result = (ReplacingPhrase) getASGElement(ctx);

		if (result == null) {
			result = new ReplacingPhraseImpl(programUnit, ctx);

			// by
			for (final InitializeReplacingByContext initializeReplacingByContext : ctx.initializeReplacingBy()) {
				result.addBy(initializeReplacingByContext);
			}

			replacingPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Call> getDataItemCalls() {
		return dataItemCalls;
	}

	@Override
	public ReplacingPhrase getReplacingPhrase() {
		return replacingPhrase;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}
}
