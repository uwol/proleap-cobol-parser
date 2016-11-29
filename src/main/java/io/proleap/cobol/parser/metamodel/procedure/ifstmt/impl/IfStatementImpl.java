/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.ifstmt.impl;

import io.proleap.cobol.Cobol85Parser.IfElseContext;
import io.proleap.cobol.Cobol85Parser.IfStatementContext;
import io.proleap.cobol.Cobol85Parser.IfThenContext;
import io.proleap.cobol.Cobol85Parser.StatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.Scope;
import io.proleap.cobol.parser.metamodel.procedure.ifstmt.Else;
import io.proleap.cobol.parser.metamodel.procedure.ifstmt.IfStatement;
import io.proleap.cobol.parser.metamodel.procedure.ifstmt.Then;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ConditionValueStmt;

public class IfStatementImpl extends StatementImpl implements IfStatement {

	protected ConditionValueStmt condition;

	protected final IfStatementContext ctx;

	protected Else ifElse;

	protected Then then;

	public IfStatementImpl(final ProgramUnit programUnit, final Scope scope, final IfStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Else addElse(final IfElseContext ctx) {
		Else result = (Else) getASGElement(ctx);

		if (result == null) {
			result = new ElseImpl(programUnit, ctx);

			// statements
			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			// next sentence
			if (ctx.SENTENCE() != null) {
				result.setNextSentence(true);
			}

			ifElse = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Then addThen(final IfThenContext ctx) {
		Then result = (Then) getASGElement(ctx);

		if (result == null) {
			result = new ThenImpl(programUnit, ctx);

			// statements
			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			// next sentence
			if (ctx.SENTENCE() != null) {
				result.setNextSentence(true);
			}

			then = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ConditionValueStmt getCondition() {
		return condition;
	}

	@Override
	public Else getElse() {
		return ifElse;
	}

	@Override
	public Then getThen() {
		return then;
	}

	@Override
	public void setCondition(final ConditionValueStmt condition) {
		this.condition = condition;
	}

}
