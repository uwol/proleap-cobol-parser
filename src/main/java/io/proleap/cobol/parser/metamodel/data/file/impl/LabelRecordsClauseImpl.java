/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.LabelRecordsClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.data.file.LabelRecordsClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class LabelRecordsClauseImpl extends CobolDivisionElementImpl implements LabelRecordsClause {

	protected final LabelRecordsClauseContext ctx;

	protected List<Call> dataCalls = new ArrayList<Call>();

	protected Type type;

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
	public Type getType() {
		return type;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
