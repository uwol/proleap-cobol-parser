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

import io.proleap.cobol.CobolParser.DataOccursClauseContext;
import io.proleap.cobol.CobolParser.DataOccursDependingContext;
import io.proleap.cobol.CobolParser.DataOccursIndexedContext;
import io.proleap.cobol.CobolParser.DataOccursSortContext;
import io.proleap.cobol.CobolParser.IndexNameContext;
import io.proleap.cobol.CobolParser.QualifiedDataNameContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursDepending;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursIndexed;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursSort;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class OccursClauseImpl extends CobolDivisionElementImpl implements OccursClause {

	protected DataOccursClauseContext ctx;

	protected ValueStmt from;

	protected OccursDepending occursDepending;

	protected OccursIndexed occursIndexed;

	protected List<OccursSort> occursSorts = new ArrayList<OccursSort>();

	protected IntegerLiteral to;

	public OccursClauseImpl(final ProgramUnit programUnit, final DataOccursClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public OccursDepending addOccursDepending(final DataOccursDependingContext ctx) {
		OccursDepending result = (OccursDepending) getASGElement(ctx);

		if (result == null) {
			result = new OccursDependingImpl(programUnit, ctx);

			if (ctx.qualifiedDataName() != null) {
				result.setDependingOnCall(createCall(ctx.qualifiedDataName()));
			}

			occursDepending = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OccursIndexed addOccursIndexed(final DataOccursIndexedContext ctx) {
		OccursIndexed result = (OccursIndexed) getASGElement(ctx);

		if (result == null) {
			result = new OccursIndexedImpl(programUnit, ctx);

			/*
			 * index names
			 */
			for (final IndexNameContext indexNameContext : ctx.indexName()) {
				result.addIndex(indexNameContext);
			}

			occursIndexed = result;
			registerASGElement(result);
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
				order = OccursSort.Order.ASCENDING;
			} else if (ctx.DESCENDING() != null) {
				order = OccursSort.Order.DESCENDING;
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
	public ValueStmt getFrom() {
		return from;
	}

	@Override
	public OccursDepending getOccursDepending() {
		return occursDepending;
	}

	@Override
	public OccursIndexed getOccursIndexed() {
		return occursIndexed;
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
	public void setFrom(final ValueStmt from) {
		this.from = from;
	}

	@Override
	public void setTo(final IntegerLiteral to) {
		this.to = to;
	}
}
