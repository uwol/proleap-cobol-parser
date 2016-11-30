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

import io.proleap.cobol.Cobol85Parser.DataOccursClauseContext;
import io.proleap.cobol.Cobol85Parser.DataOccursSortContext;
import io.proleap.cobol.Cobol85Parser.IndexNameContext;
import io.proleap.cobol.Cobol85Parser.QualifiedDataNameContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.data.datadescription.OccursClause;
import io.proleap.cobol.parser.metamodel.data.datadescription.OccursSort;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class OccursClauseImpl extends CobolDivisionElementImpl implements OccursClause {

	protected DataOccursClauseContext ctx;

	protected Call dependingOnCall;

	protected IntegerLiteral from;

	protected List<Call> indexCalls = new ArrayList<Call>();

	protected List<OccursSort> occursSorts = new ArrayList<OccursSort>();

	protected IntegerLiteral to;

	public OccursClauseImpl(final ProgramUnit programUnit, final DataOccursClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt addIndexCall(final IndexNameContext ctx) {
		final ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			final Call indexCall = createCall(ctx);
			indexCalls.add(indexCall);
		}

		return result;
	}

	@Override
	public OccursSort addOccursSort(final DataOccursSortContext ctx) {
		OccursSort result = (OccursSort) getASGElement(ctx);

		if (result == null) {
			result = new OccursSortImpl(programUnit, ctx);

			/*
			 * order
			 */
			final OccursSort.Order order;

			if (ctx.ASCENDING() != null) {
				order = OccursSort.Order.Ascending;
			} else if (ctx.DESCENDING() != null) {
				order = OccursSort.Order.Descending;
			} else {
				order = null;
			}

			result.setOrder(order);

			/*
			 * qualified data names
			 */
			for (final QualifiedDataNameContext qualifiedDataNameContext : ctx.qualifiedDataName()) {
				final Call keyCall = createCall(qualifiedDataNameContext);
				result.addKeyCall(keyCall);
			}

			occursSorts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call getDependingOnCall() {
		return dependingOnCall;
	}

	@Override
	public IntegerLiteral getFrom() {
		return from;
	}

	@Override
	public List<Call> getIndexCalls() {
		return indexCalls;
	}

	@Override
	public List<OccursSort> getOccursSorts() {
		return occursSorts;
	}

	@Override
	public IntegerLiteral getTo() {
		return to;
	}

	@Override
	public void setDependingOnCall(final Call dependingOnCall) {
		this.dependingOnCall = dependingOnCall;
	}

	@Override
	public void setFrom(final IntegerLiteral from) {
		this.from = from;
	}

	@Override
	public void setTo(final IntegerLiteral to) {
		this.to = to;
	}

}
