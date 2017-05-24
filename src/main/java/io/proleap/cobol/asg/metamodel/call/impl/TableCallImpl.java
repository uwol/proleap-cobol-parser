/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.SubscriptContext;
import io.proleap.cobol.Cobol85Parser.TableCallContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.TableCall;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.valuestmt.Subscript;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.SubscriptImpl;

public class TableCallImpl extends CallImpl implements TableCall {

	protected final CallType callType = CallType.TABLE_CALL;

	protected final TableCallContext ctx;

	protected DataDescriptionEntry dataDescriptionEntry;

	protected List<Subscript> subscripts = new ArrayList<Subscript>();

	public TableCallImpl(final String name, final DataDescriptionEntry dataDescriptionEntry,
			final ProgramUnit programUnit, final TableCallContext ctx) {
		super(name, programUnit, ctx);

		this.ctx = ctx;
		this.dataDescriptionEntry = dataDescriptionEntry;
	}

	@Override
	public Subscript addSubscript(final SubscriptContext ctx) {
		Subscript result = (Subscript) getASGElement(ctx);

		if (result == null) {
			result = new SubscriptImpl(programUnit, ctx);

			final ValueStmt subscriptValueStmt = createValueStmt(ctx.integerLiteral(), ctx.qualifiedDataName(),
					ctx.indexName(), ctx.arithmeticExpression());
			result.setSubscriptValueStmt(subscriptValueStmt);

			subscripts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CallType getCallType() {
		return callType;
	}

	@Override
	public TableCallContext getCtx() {
		return ctx;
	}

	@Override
	public DataDescriptionEntry getDataDescriptionEntry() {
		return dataDescriptionEntry;
	}

	@Override
	public List<Subscript> getSubscripts() {
		return subscripts;
	}
}
