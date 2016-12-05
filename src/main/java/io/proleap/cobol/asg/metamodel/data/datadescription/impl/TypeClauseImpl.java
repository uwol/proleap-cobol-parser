/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataTypeClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.TypeClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class TypeClauseImpl extends CobolDivisionElementImpl implements TypeClause {

	protected DataTypeClauseContext ctx;

	protected TimeType timeType;

	public TypeClauseImpl(final ProgramUnit programUnit, final DataTypeClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public TimeType getTimeType() {
		return timeType;
	}

	@Override
	public void setTimeType(final TimeType timeType) {
		this.timeType = timeType;
	}

}
