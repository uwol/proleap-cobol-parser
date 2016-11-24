/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.compute.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.ComputeStatementContext;
import io.proleap.cobol.Cobol85Parser.ComputeStoreContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.parser.metamodel.procedure.OnSizeError;
import io.proleap.cobol.parser.metamodel.procedure.compute.ComputeStatement;
import io.proleap.cobol.parser.metamodel.procedure.compute.Store;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ArithmeticValueStmt;

public class ComputeStatementImpl extends StatementImpl implements ComputeStatement {

	private final static Logger LOG = LogManager.getLogger(ComputeStatementImpl.class);

	protected ArithmeticValueStmt arithmeticExpression;

	protected final ComputeStatementContext ctx;

	protected NotOnSizeError notOnSizeError;

	protected OnSizeError onSizeError;

	protected List<Store> stores = new ArrayList<Store>();

	public ComputeStatementImpl(final ProgramUnit programUnit, final ComputeStatementContext ctx) {
		super(programUnit, ctx);

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
			final Call storeCall = createCall(ctx.identifier());
			result.setStoreCall(storeCall);

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
	public NotOnSizeError getNotOnSizeError() {
		return notOnSizeError;
	}

	@Override
	public OnSizeError getOnSizeError() {
		return onSizeError;
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
	public void setNotOnSizeError(final NotOnSizeError notOnSizeError) {
		this.notOnSizeError = notOnSizeError;
	}

	@Override
	public void setOnSizeError(final OnSizeError onSizeError) {
		this.onSizeError = onSizeError;
	}

}
