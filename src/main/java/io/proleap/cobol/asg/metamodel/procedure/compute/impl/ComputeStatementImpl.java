/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.compute.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.ComputeStatementContext;
import io.proleap.cobol.CobolParser.ComputeStoreContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.compute.ComputeStatement;
import io.proleap.cobol.asg.metamodel.procedure.compute.Store;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;

public class ComputeStatementImpl extends StatementImpl implements ComputeStatement {

	protected ArithmeticValueStmt arithmeticExpression;

	protected final ComputeStatementContext ctx;

	protected NotOnSizeErrorPhrase notOnSizeErrorPhrase;

	protected OnSizeErrorPhrase onSizeErrorPhrase;

	protected final StatementType statementType = StatementTypeEnum.COMPUTE;

	protected List<Store> stores = new ArrayList<Store>();

	public ComputeStatementImpl(final ProgramUnit programUnit, final Scope scope, final ComputeStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Store addStore(final ComputeStoreContext ctx) {
		Store result = (Store) getASGElement(ctx);

		if (result == null) {
			result = new StoreImpl(programUnit, ctx);

			// rounded
			if (ctx.ROUNDED() != null) {
				result.setRounded(true);
			}

			// store target call
			if (ctx.identifier() != null) {
				final Call storeCall = createCall(ctx.identifier());
				result.setStoreCall(storeCall);
			}

			stores.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ArithmeticValueStmt getArithmeticExpression() {
		return arithmeticExpression;
	}

	@Override
	public NotOnSizeErrorPhrase getNotOnSizeErrorPhrase() {
		return notOnSizeErrorPhrase;
	}

	@Override
	public OnSizeErrorPhrase getOnSizeErrorPhrase() {
		return onSizeErrorPhrase;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public List<Store> getStores() {
		return stores;
	}

	@Override
	public void setArithmeticExpression(final ArithmeticValueStmt arithmeticExpression) {
		this.arithmeticExpression = arithmeticExpression;
	}

	@Override
	public void setNotOnSizeErrorPhrase(final NotOnSizeErrorPhrase notOnSizeErrorPhrase) {
		this.notOnSizeErrorPhrase = notOnSizeErrorPhrase;
	}

	@Override
	public void setOnSizeErrorPhrase(final OnSizeErrorPhrase onSizeErrorPhrase) {
		this.onSizeErrorPhrase = onSizeErrorPhrase;
	}

}
