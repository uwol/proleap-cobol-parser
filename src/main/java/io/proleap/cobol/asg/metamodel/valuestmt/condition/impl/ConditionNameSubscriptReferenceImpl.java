/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.ConditionNameSubscriptReferenceContext;
import io.proleap.cobol.Cobol85Parser.SubscriptContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.Subscript;
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

			// TODO

			subscripts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Subscript> getSubscripts() {
		return subscripts;
	}

	@Override
	public String getValue() {
		return null;
	}

}
