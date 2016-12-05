/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataRecordAreaClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.RecordAreaClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class RecordAreaClauseImpl extends CobolDivisionElementImpl implements RecordAreaClause {

	protected DataRecordAreaClauseContext ctx;

	protected boolean recordArea;

	public RecordAreaClauseImpl(final ProgramUnit programUnit, final DataRecordAreaClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isRecordArea() {
		return recordArea;
	}

	@Override
	public void setRecordArea(final boolean recordArea) {
		this.recordArea = recordArea;
	}

}
