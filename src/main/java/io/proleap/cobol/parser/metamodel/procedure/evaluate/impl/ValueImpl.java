/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.evaluate.impl;

import io.proleap.cobol.Cobol85Parser.EvaluateValueContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.Value;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class ValueImpl extends CobolDivisionElementImpl implements Value {

	protected final EvaluateValueContext ctx;

	protected ValueStmt valueStmt;

	public ValueImpl(final ProgramUnit programUnit, final EvaluateValueContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getValueStmt() {
		return valueStmt;
	}

	@Override
	public void setValueStmt(final ValueStmt valueStmt) {
		this.valueStmt = valueStmt;
	}

}
