/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.CobolParser.RelationalOperatorContext;
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
			final RelationalOperator.RelationalOperatorType type;

			if (ctx.MORETHANOREQUAL() != null) {
				type = RelationalOperator.RelationalOperatorType.GREATER_OR_EQUAL;
			} else if (ctx.LESSTHANOREQUAL() != null) {
				type = RelationalOperator.RelationalOperatorType.LESS_OR_EQUAL;
			} else if (ctx.GREATER() != null && ctx.EQUAL() != null) {
				type = RelationalOperator.RelationalOperatorType.GREATER_OR_EQUAL;
			} else if (ctx.LESS() != null && ctx.EQUAL() != null) {
				type = RelationalOperator.RelationalOperatorType.LESS_OR_EQUAL;
			}
			// with not
			else if (ctx.NOT() != null && ctx.MORETHANCHAR() != null) {
				type = RelationalOperator.RelationalOperatorType.LESS_OR_EQUAL;
			} else if (ctx.NOT() != null && ctx.LESSTHANCHAR() != null) {
				type = RelationalOperator.RelationalOperatorType.GREATER_OR_EQUAL;
			} else if (ctx.NOT() != null && ctx.GREATER() != null) {
				type = RelationalOperator.RelationalOperatorType.LESS_OR_EQUAL;
			} else if (ctx.NOT() != null && ctx.LESS() != null) {
				type = RelationalOperator.RelationalOperatorType.GREATER_OR_EQUAL;
			} else if (ctx.NOT() != null && ctx.EQUAL() != null) {
				type = RelationalOperator.RelationalOperatorType.NOT_EQUAL;
			} else if (ctx.NOT() != null && ctx.EQUALCHAR() != null) {
				type = RelationalOperator.RelationalOperatorType.NOT_EQUAL;
			}
			// without not
			else if (ctx.NOTEQUALCHAR() != null) {
				type = RelationalOperator.RelationalOperatorType.NOT_EQUAL;
			} else if (ctx.MORETHANCHAR() != null) {
				type = RelationalOperator.RelationalOperatorType.GREATER;
			} else if (ctx.LESSTHANCHAR() != null) {
				type = RelationalOperator.RelationalOperatorType.LESS;
			} else if (ctx.GREATER() != null) {
				type = RelationalOperator.RelationalOperatorType.GREATER;
			} else if (ctx.LESS() != null) {
				type = RelationalOperator.RelationalOperatorType.LESS;
			} else if (ctx.EQUAL() != null) {
				type = RelationalOperator.RelationalOperatorType.EQUAL;
			} else if (ctx.EQUALCHAR() != null) {
				type = RelationalOperator.RelationalOperatorType.EQUAL;
			} else {
				type = null;
			}

			result.setRelationalOperatorType(type);

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
