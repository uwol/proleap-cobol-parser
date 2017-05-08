/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.impl;

import io.proleap.cobol.Cobol85Parser.SubscriptContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.Subscript;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class SubscriptImpl extends CobolDivisionElementImpl implements Subscript {

	protected SubscriptContext ctx;

	protected ValueStmt subscriptValueStmt;

	public SubscriptImpl(final ProgramUnit programUnit, final SubscriptContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getSubscriptValueStmt() {
		return subscriptValueStmt;
	}

	@Override
	public void setSubscriptValueStmt(final ValueStmt subscriptValueStmt) {
		this.subscriptValueStmt = subscriptValueStmt;
	}

}
