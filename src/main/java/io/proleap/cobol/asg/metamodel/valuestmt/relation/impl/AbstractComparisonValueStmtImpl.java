/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.Cobol85Parser.RelationalOperatorContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.ComparisonStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.RelationalOperator;

public abstract class AbstractComparisonValueStmtImpl extends ValueStmtImpl implements ComparisonStmt {

	protected RelationalOperator operator;

	public AbstractComparisonValueStmtImpl(final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public RelationalOperator addOperator(final RelationalOperatorContext ctx) {
		RelationalOperator result = (RelationalOperator) getASGElement(ctx);

		if (result == null) {
			result = new RelationalOperatorImpl(programUnit, ctx);

			// operator
			final RelationalOperator.Type type;

			if (ctx.MORETHANOREQUAL() != null) {
				type = RelationalOperator.Type.GREATER_OR_EQUAL;
			} else if (ctx.LESSTHANOREQUAL() != null) {
				type = RelationalOperator.Type.LESS_OR_EQUAL;
			} else if (ctx.GREATER() != null && ctx.EQUAL() != null) {
				type = RelationalOperator.Type.GREATER_OR_EQUAL;
			} else if (ctx.LESS() != null && ctx.EQUAL() != null) {
				type = RelationalOperator.Type.LESS_OR_EQUAL;
			}
			// with not
			else if (ctx.NOT() != null && ctx.MORETHANCHAR() != null) {
				type = RelationalOperator.Type.LESS_OR_EQUAL;
			} else if (ctx.NOT() != null && ctx.LESSTHANCHAR() != null) {
				type = RelationalOperator.Type.GREATER_OR_EQUAL;
			} else if (ctx.NOT() != null && ctx.GREATER() != null) {
				type = RelationalOperator.Type.LESS_OR_EQUAL;
			} else if (ctx.NOT() != null && ctx.LESS() != null) {
				type = RelationalOperator.Type.GREATER_OR_EQUAL;
			} else if (ctx.NOT() != null && ctx.EQUAL() != null) {
				type = RelationalOperator.Type.NOT_EQUAL;
			} else if (ctx.NOT() != null && ctx.EQUALCHAR() != null) {
				type = RelationalOperator.Type.NOT_EQUAL;
			}
			// without not
			else if (ctx.MORETHANCHAR() != null) {
				type = RelationalOperator.Type.GREATER;
			} else if (ctx.LESSTHANCHAR() != null) {
				type = RelationalOperator.Type.LESS;
			} else if (ctx.GREATER() != null) {
				type = RelationalOperator.Type.GREATER;
			} else if (ctx.LESS() != null) {
				type = RelationalOperator.Type.LESS;
			} else if (ctx.EQUAL() != null) {
				type = RelationalOperator.Type.EQUAL;
			} else if (ctx.EQUALCHAR() != null) {
				type = RelationalOperator.Type.EQUAL;
			} else {
				type = null;
			}

			result.setType(type);

			operator = result;
			subValueStmts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public RelationalOperator getOperator() {
		return operator;
	}
}
