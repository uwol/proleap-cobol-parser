/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.DataValueClauseContext;
import io.proleap.cobol.Cobol85Parser.DataValueIntervalContext;
import io.proleap.cobol.Cobol85Parser.DataValueIntervalFromContext;
import io.proleap.cobol.Cobol85Parser.DataValueIntervalToContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.ValueClause;
import io.proleap.cobol.parser.metamodel.data.datadescription.ValueInterval;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class ValueClauseImpl extends CobolDivisionElementImpl implements ValueClause {

	private final static Logger LOG = LogManager.getLogger(ValueClauseImpl.class);

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
			final ValueStmt fromValueStmt;

			if (fromContext.literal() != null) {
				fromValueStmt = createLiteralValueStmt(fromContext.literal());
			} else if (fromContext.cobolWord() != null) {
				fromValueStmt = createCallValueStmt(fromContext.cobolWord());
			} else {
				LOG.warn("unknown from at {}", ctx);
				fromValueStmt = null;
			}

			result.setFrom(fromValueStmt);

			/*
			 * to
			 */
			final DataValueIntervalToContext toContext = ctx.dataValueIntervalTo();

			if (toContext != null) {
				final ValueStmt toValueStmt;

				if (toContext.literal() != null) {
					toValueStmt = createLiteralValueStmt(toContext.literal());
				} else {
					LOG.warn("unknown to at {}", ctx);
					toValueStmt = null;
				}

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
