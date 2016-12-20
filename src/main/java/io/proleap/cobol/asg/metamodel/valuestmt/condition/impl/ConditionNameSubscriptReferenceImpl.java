/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition.impl;

import io.proleap.cobol.Cobol85Parser.ConditionNameSubscriptReferenceContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.ConditionNameSubscriptReference;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class ConditionNameSubscriptReferenceImpl extends ValueStmtImpl implements ConditionNameSubscriptReference {

	protected ConditionNameSubscriptReferenceContext ctx;

	public ConditionNameSubscriptReferenceImpl(final ProgramUnit programUnit,
			final ConditionNameSubscriptReferenceContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public String getValue() {
		return null;
	}

}
