/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition.impl;

import io.proleap.cobol.Cobol85Parser.ConditionNameReferenceContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.ConditionNameReference;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class ConditionNameReferenceImpl extends ValueStmtImpl implements ConditionNameReference {

	protected ConditionNameReferenceContext ctx;

	public ConditionNameReferenceImpl(final ProgramUnit programUnit, final ConditionNameReferenceContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public String getValue() {
		return null;
	}

}
