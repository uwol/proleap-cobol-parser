/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.impl;

import io.proleap.cobol.Cobol85Parser.RelationConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.RelationConditionValueStmt;

public class RelationConditionValueStmtImpl extends ValueStmtImpl implements RelationConditionValueStmt {

	protected RelationConditionContext ctx;

	public RelationConditionValueStmtImpl(final ProgramUnit programUnit, final RelationConditionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public String getValue() {
		return null;
	}

}
