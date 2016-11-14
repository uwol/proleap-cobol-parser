/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.configuration.object.impl;

import io.proleap.cobol.Cobol85Parser.SegmentLimitClauseContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.configuration.object.SegmentLimitClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class SegmentLimitClauseImpl extends CobolDivisionElementImpl implements SegmentLimitClause {

	protected final SegmentLimitClauseContext ctx;

	protected IntegerLiteral integerLiteral;

	public SegmentLimitClauseImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final SegmentLimitClauseContext ctx) {
		super(programUnit, scope, ctx);

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
