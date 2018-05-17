/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.CobolParser.RecordDelimiterClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.RecordDelimiterClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class RecordDelimiterClauseImpl extends CobolDivisionElementImpl implements RecordDelimiterClause {

	protected final RecordDelimiterClauseContext ctx;

	protected RecordDelimiterClauseType recordDelimiterClauseType;

	protected ValueStmt valueStmt;

	public RecordDelimiterClauseImpl(final ProgramUnit programUnit, final RecordDelimiterClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public RecordDelimiterClauseType getRecordDelimiterClauseType() {
		return recordDelimiterClauseType;
	}

	@Override
	public ValueStmt getValueStmt() {
		return valueStmt;
	}

	@Override
	public void setRecordDelimiterClauseType(final RecordDelimiterClauseType recordDelimiterClauseType) {
		this.recordDelimiterClauseType = recordDelimiterClauseType;
	}

	@Override
	public void setValueStmt(final ValueStmt valueStmt) {
		this.valueStmt = valueStmt;
	}

}
