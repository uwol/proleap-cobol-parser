/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect.impl;

import io.proleap.cobol.CobolParser.InspectBeforeAfterContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.inspect.BeforeAfterPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class BeforeAfterPhraseImpl extends InspectPhraseImpl implements BeforeAfterPhrase {

	protected BeforeAfterType beforeAfterType;

	protected final InspectBeforeAfterContext ctx;

	protected ValueStmt dataItemValueStmt;

	public BeforeAfterPhraseImpl(final ProgramUnit programUnit, final InspectBeforeAfterContext ctx) {
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
