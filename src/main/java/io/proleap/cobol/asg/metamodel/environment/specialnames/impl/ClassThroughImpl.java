/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames.impl;

import io.proleap.cobol.CobolParser.ClassClauseThroughContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.specialnames.ClassThrough;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ClassThroughImpl extends CobolDivisionElementImpl implements ClassThrough {

	protected final ClassClauseThroughContext ctx;

	protected ValueStmt from;

	protected ValueStmt to;

	public ClassThroughImpl(final ProgramUnit programUnit, final ClassClauseThroughContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getFrom() {
		return from;
	}

	@Override
	public ValueStmt getTo() {
		return to;
	}

	@Override
	public void setFrom(final ValueStmt from) {
		this.from = from;
	}

	@Override
	public void setTo(final ValueStmt to) {
		this.to = to;
	}

}
