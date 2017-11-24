/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation.impl;

import io.proleap.cobol.Cobol85Parser.RelationalOperatorContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.type.CobolBaseType;
import io.proleap.cobol.asg.metamodel.type.Type;
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
	public Type getType() {
		return CobolBaseType.BOOLEAN;
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public void setRelationalOperatorType(final RelationalOperatorType relationalOperatorType) {
		this.relationalOperatorType = relationalOperatorType;
	}
}
