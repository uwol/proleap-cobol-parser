/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.ConditionNameSubscriptReferenceContext;
import io.proleap.cobol.CobolParser.SubscriptContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.Subscript;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.ConditionNameSubscriptReference;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.SubscriptImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class ConditionNameSubscriptReferenceImpl extends ValueStmtImpl implements ConditionNameSubscriptReference {

	protected ConditionNameSubscriptReferenceContext ctx;

	protected List<Subscript> subscripts = new ArrayList<Subscript>();

	public ConditionNameSubscriptReferenceImpl(final ProgramUnit programUnit,
			final ConditionNameSubscriptReferenceContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public Subscript addSubscript(final SubscriptContext ctx) {
		Subscript result = (Subscript) getASGElement(ctx);

		if (result == null) {
			result = new SubscriptImpl(programUnit, ctx);

			final ValueStmt subscriptValueStmt = createValueStmt(ctx.integerLiteral(), ctx.qualifiedDataName(),
					ctx.indexName(), ctx.arithmeticExpression());
			result.setSubscriptValueStmt(subscriptValueStmt);

			subscripts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Subscript> getSubscripts() {
		return subscripts;
	}
}
