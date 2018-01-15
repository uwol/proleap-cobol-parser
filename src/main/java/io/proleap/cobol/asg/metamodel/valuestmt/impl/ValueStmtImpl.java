/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public abstract class ValueStmtImpl extends CobolDivisionElementImpl implements ValueStmt {

	protected final ParserRuleContext ctx;

	protected final List<ValueStmt> subValueStmts = new ArrayList<ValueStmt>();

	public ValueStmtImpl(final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addSubValueStmt(final ValueStmt valueStmt) {
		subValueStmts.add(valueStmt);
	}

	@Override
	public List<ValueStmt> getSubValueStmts() {
		return subValueStmts;
	}
}
