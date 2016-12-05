/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataValueIntervalContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.ValueInterval;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ValueIntervalImpl extends CobolDivisionElementImpl implements ValueInterval {

	protected DataValueIntervalContext ctx;

	protected ValueStmt fromValueStmt;

	protected ValueStmt toValueStmt;

	public ValueIntervalImpl(final ProgramUnit programUnit, final DataValueIntervalContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getFromValueStmt() {
		return fromValueStmt;
	}

	@Override
	public ValueStmt getToValueStmt() {
		return toValueStmt;
	}

	@Override
	public void setFrom(final ValueStmt fromValueStmt) {
		this.fromValueStmt = fromValueStmt;
	}

	@Override
	public void setTo(final ValueStmt toValueStmt) {
		this.toValueStmt = toValueStmt;
	}

}
