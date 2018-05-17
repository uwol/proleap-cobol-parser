/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.impl;

import io.proleap.cobol.CobolParser.RerunEveryRecordsContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.RerunEveryRecords;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class RerunEveryRecordsImpl extends CobolDivisionElementImpl implements RerunEveryRecords {

	protected final RerunEveryRecordsContext ctx;

	protected IntegerLiteral records;

	public RerunEveryRecordsImpl(final ProgramUnit programUnit, final RerunEveryRecordsContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getRecords() {
		return records;
	}

	@Override
	public void setRecords(final IntegerLiteral records) {
		this.records = records;
	}

}
