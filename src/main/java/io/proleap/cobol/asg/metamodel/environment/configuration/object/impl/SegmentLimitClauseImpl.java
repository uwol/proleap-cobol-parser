/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.configuration.object.impl;

import io.proleap.cobol.CobolParser.SegmentLimitClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.SegmentLimitClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class SegmentLimitClauseImpl extends CobolDivisionElementImpl implements SegmentLimitClause {

	protected final SegmentLimitClauseContext ctx;

	protected IntegerLiteral integerLiteral;

	public SegmentLimitClauseImpl(final ProgramUnit programUnit, final SegmentLimitClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getIntegerLiteral() {
		return integerLiteral;
	}

	@Override
	public void setIntegerLiteral(final IntegerLiteral integerLiteral) {
		this.integerLiteral = integerLiteral;
	}

}
