/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.LabelRecordsClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.file.LabelRecordsClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class LabelRecordsClauseImpl extends CobolDivisionElementImpl implements LabelRecordsClause {

	protected final LabelRecordsClauseContext ctx;

	protected List<Call> dataCalls = new ArrayList<Call>();

	protected LabelRecordsClauseType labelRecordsClauseType;

	public LabelRecordsClauseImpl(final ProgramUnit programUnit, final LabelRecordsClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addDataCall(final Call dataCall) {
		dataCalls.add(dataCall);
	}

	@Override
	public List<Call> getDataCalls() {
		return dataCalls;
	}

	@Override
	public LabelRecordsClauseType getLabelRecordsClauseType() {
		return labelRecordsClauseType;
	}

	@Override
	public void setLabelRecordsClauseType(final LabelRecordsClauseType labelRecordsClauseType) {
		this.labelRecordsClauseType = labelRecordsClauseType;
	}

}
