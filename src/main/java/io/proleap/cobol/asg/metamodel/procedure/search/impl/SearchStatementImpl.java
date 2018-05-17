/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.search.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.SearchStatementContext;
import io.proleap.cobol.CobolParser.SearchVaryingContext;
import io.proleap.cobol.CobolParser.SearchWhenContext;
import io.proleap.cobol.CobolParser.StatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.AtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.search.SearchStatement;
import io.proleap.cobol.asg.metamodel.procedure.search.VaryingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.search.WhenPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;

public class SearchStatementImpl extends StatementImpl implements SearchStatement {

	protected AtEndPhrase atEndPhrase;

	protected final SearchStatementContext ctx;

	protected Call dataCall;

	protected final StatementType statementType = StatementTypeEnum.SEARCH;

	protected VaryingPhrase varyingPhrase;

	protected List<WhenPhrase> whenPhrases = new ArrayList<WhenPhrase>();

	public SearchStatementImpl(final ProgramUnit programUnit, final Scope scope, final SearchStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public VaryingPhrase addVaryingPhrase(final SearchVaryingContext ctx) {
		VaryingPhrase result = (VaryingPhrase) getASGElement(ctx);

		if (result == null) {
			result = new VaryingImpl(programUnit, ctx);

			final Call dataCall = createCall(ctx.qualifiedDataName());
			result.setDataCall(dataCall);

			varyingPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public WhenPhrase addWhenPhrase(final SearchWhenContext ctx) {
		WhenPhrase result = (WhenPhrase) getASGElement(ctx);

		if (result == null) {
			result = new WhenImpl(programUnit, ctx);

			// condition
			final ConditionValueStmt condition = createConditionValueStmt(ctx.condition());
			result.setCondition(condition);

			// type and statements
			final WhenPhrase.WhenType type;

			if (ctx.NEXT() != null) {
				type = WhenPhrase.WhenType.NEXT_SENTENCE;
			} else if (!ctx.statement().isEmpty()) {
				for (final StatementContext statementContext : ctx.statement()) {
					result.addStatement(statementContext);
				}

				type = WhenPhrase.WhenType.STATEMENTS;
			} else {
				type = null;
			}

			result.setWhenType(type);

			whenPhrases.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AtEndPhrase getAtEndPhrase() {
		return atEndPhrase;
	}

	@Override
	public Call getDataCall() {
		return dataCall;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public VaryingPhrase getVaryingPhrase() {
		return varyingPhrase;
	}

	@Override
	public List<WhenPhrase> getWhenPhrases() {
		return whenPhrases;
	}

	@Override
	public void setAtEndPhrase(final AtEndPhrase atEndPhrase) {
		this.atEndPhrase = atEndPhrase;
	}

	@Override
	public void setDataCall(final Call dataCall) {
		this.dataCall = dataCall;
	}

}
