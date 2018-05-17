/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation.impl;

import io.proleap.cobol.CobolParser.RelationalOperatorContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.RelationalOperator;

public class RelationalOperatorImpl extends ValueStmtImpl implements RelationalOperator {

	protected RelationalOperatorContext ctx;

	protected RelationalOperatorType relationalOperatorType;

	public RelationalOperatorImpl(final ProgramUnit programUnit, final RelationalOperatorContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public RelationalOperatorType getRelationalOperatorType() {
		return relationalOperatorType;
	}

	@Override
	public void setRelationalOperatorType(final RelationalOperatorType relationalOperatorType) {
		this.relationalOperatorType = relationalOperatorType;
	}
}
