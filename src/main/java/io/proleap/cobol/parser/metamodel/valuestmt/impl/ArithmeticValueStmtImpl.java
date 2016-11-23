/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.valuestmt.impl;

import io.proleap.cobol.Cobol85Parser.ArithmeticExpressionContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.valuestmt.ArithmeticValueStmt;

public class ArithmeticValueStmtImpl extends ValueStmtImpl implements ArithmeticValueStmt {

	protected ArithmeticExpressionContext ctx;

	public ArithmeticValueStmtImpl(final ProgramUnit programUnit, final ArithmeticExpressionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public String getValue() {
		return null;
	}

}
