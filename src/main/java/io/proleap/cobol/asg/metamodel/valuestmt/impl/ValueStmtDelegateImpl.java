/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.type.Type;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ValueStmtDelegateImpl extends ValueStmtImpl implements ValueStmt {

	protected final ValueStmt delegate;

	public ValueStmtDelegateImpl(final ValueStmt delegate, final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(programUnit, ctx);

		this.delegate = delegate;
	}

	@Override
	public Type getType() {
		return delegate.getType();
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public String toString() {
		return delegate.toString();
	}

}
