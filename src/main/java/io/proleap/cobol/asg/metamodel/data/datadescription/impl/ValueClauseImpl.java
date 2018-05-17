/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.DataValueClauseContext;
import io.proleap.cobol.CobolParser.DataValueIntervalContext;
import io.proleap.cobol.CobolParser.DataValueIntervalFromContext;
import io.proleap.cobol.CobolParser.DataValueIntervalToContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.ValueClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.ValueInterval;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ValueClauseImpl extends CobolDivisionElementImpl implements ValueClause {

	protected DataValueClauseContext ctx;

	protected List<ValueInterval> valueIntervals = new ArrayList<ValueInterval>();

	public ValueClauseImpl(final ProgramUnit programUnit, final DataValueClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueInterval addValueInterval(final DataValueIntervalContext ctx) {
		ValueInterval result = (ValueInterval) getASGElement(ctx);

		if (result == null) {
			result = new ValueIntervalImpl(programUnit, ctx);

			/*
			 * from
			 */
			final DataValueIntervalFromContext fromContext = ctx.dataValueIntervalFrom();
			final ValueStmt fromValueStmt = createValueStmt(fromContext.literal(), fromContext.cobolWord());
			result.setFrom(fromValueStmt);

			/*
			 * to
			 */
			final DataValueIntervalToContext toContext = ctx.dataValueIntervalTo();

			if (toContext != null) {
				final ValueStmt toValueStmt = createValueStmt(toContext.literal());
				result.setTo(toValueStmt);
			}

			valueIntervals.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<ValueInterval> getValueIntervals() {
		return valueIntervals;
	}

}
