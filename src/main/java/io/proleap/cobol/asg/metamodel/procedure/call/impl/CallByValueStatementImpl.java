/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.CallByValueContext;
import io.proleap.cobol.Cobol85Parser.CallByValueStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.call.CallByValueStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class CallByValueStatementImpl extends CobolDivisionElementImpl implements CallByValueStatement {

	protected final CallByValueStatementContext ctx;

	protected List<ValueStmt> valueStmts = new ArrayList<ValueStmt>();

	public CallByValueStatementImpl(final ProgramUnit programUnit, final CallByValueStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt addValueStmt(final CallByValueContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = createValueStmt(ctx.literal(), ctx.identifier());

			valueStmts.add(result);
		}

		return result;
	}

	@Override
	public List<ValueStmt> getValueStmts() {
		return valueStmts;
	}

}
