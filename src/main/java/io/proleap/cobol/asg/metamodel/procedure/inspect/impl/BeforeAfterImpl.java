/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect.impl;

import io.proleap.cobol.Cobol85Parser.InspectBeforeAfterContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.inspect.BeforeAfter;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class BeforeAfterImpl extends InspectPhraseImpl implements BeforeAfter {

	protected BeforeAfterType beforeAfterType;

	protected final InspectBeforeAfterContext ctx;

	protected ValueStmt dataItemValueStmt;

	public BeforeAfterImpl(final ProgramUnit programUnit, final InspectBeforeAfterContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public BeforeAfterType getBeforeAfterType() {
		return beforeAfterType;
	}

	@Override
	public ValueStmt getDataItemValueStmt() {
		return dataItemValueStmt;
	}

	@Override
	public void setBeforeAfterType(final BeforeAfterType beforeAfterType) {
		this.beforeAfterType = beforeAfterType;
	}

	@Override
	public void setDataItemValueStmt(final ValueStmt dataItemValueStmt) {
		this.dataItemValueStmt = dataItemValueStmt;
	}

}
