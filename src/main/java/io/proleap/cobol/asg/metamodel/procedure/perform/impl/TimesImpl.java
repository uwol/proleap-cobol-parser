/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import io.proleap.cobol.Cobol85Parser.PerformTimesContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.Times;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class TimesImpl extends CobolDivisionElementImpl implements Times {

	protected final PerformTimesContext ctx;

	protected ValueStmt timesValueStmt;

	public TimesImpl(final ProgramUnit programUnit, final PerformTimesContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getTimesValueStmt() {
		return timesValueStmt;
	}

	@Override
	public void setTimesValueStmt(final ValueStmt timesValueStmt) {
		this.timesValueStmt = timesValueStmt;
	}

}
