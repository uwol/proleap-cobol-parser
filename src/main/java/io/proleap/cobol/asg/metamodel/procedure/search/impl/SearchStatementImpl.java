/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.search.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.SearchStatementContext;
import io.proleap.cobol.Cobol85Parser.SearchVaryingContext;
import io.proleap.cobol.Cobol85Parser.SearchWhenContext;
import io.proleap.cobol.Cobol85Parser.StatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.AtEnd;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.search.SearchStatement;
import io.proleap.cobol.asg.metamodel.procedure.search.Varying;
import io.proleap.cobol.asg.metamodel.procedure.search.When;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;

public class SearchStatementImpl extends StatementImpl implements SearchStatement {

	protected AtEnd atEnd;

	protected final SearchStatementContext ctx;

	protected Call dataCall;

	protected final StatementType statementType = StatementTypeEnum.SEARCH;

	protected Varying varying;

	protected List<When> whens = new ArrayList<When>();

	public SearchStatementImpl(final ProgramUnit programUnit, final Scope scope, final SearchStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Varying addVarying(final SearchVaryingContext ctx) {
		Varying result = (Varying) getASGElement(ctx);

		if (result == null) {
			result = new VaryingImpl(programUnit, ctx);

			final Call dataCall = createCall(ctx.qualifiedDataName());
			result.setDataCall(dataCall);

			varying = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public When addWhen(final SearchWhenContext ctx) {
		When result = (When) getASGElement(ctx);

		if (result == null) {
			result = new WhenImpl(programUnit, ctx);

			// condition
			final ConditionValueStmt condition = createConditionValueStmt(ctx.condition());
			result.setCondition(condition);

			// type and statements
			final When.Type type;

			if (ctx.NEXT() != null) {
				type = When.Type.NEXT_SENTENCE;
			} else if (!ctx.statement().isEmpty()) {
				for (final StatementContext statementContext : ctx.statement()) {
					result.addStatement(statementContext);
				}

				type = When.Type.STATEMENTS;
			} else {
				type = null;
			}

			result.setType(type);

			whens.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AtEnd getAtEnd() {
		return atEnd;
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
	public Varying getVarying() {
		return varying;
	}

	@Override
	public List<When> getWhens() {
		return whens;
	}

	@Override
	public void setAtEnd(final AtEnd atEnd) {
		this.atEnd = atEnd;
	}

	@Override
	public void setDataCall(final Call dataCall) {
		this.dataCall = dataCall;
	}

}
