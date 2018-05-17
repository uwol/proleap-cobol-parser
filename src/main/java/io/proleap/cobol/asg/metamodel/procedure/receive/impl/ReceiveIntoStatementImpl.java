/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive.impl;

import io.proleap.cobol.CobolParser.ReceiveIntoStatementContext;
import io.proleap.cobol.CobolParser.ReceiveNoDataContext;
import io.proleap.cobol.CobolParser.ReceiveWithDataContext;
import io.proleap.cobol.CobolParser.StatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.receive.NoData;
import io.proleap.cobol.asg.metamodel.procedure.receive.ReceiveIntoStatement;
import io.proleap.cobol.asg.metamodel.procedure.receive.WithData;

public class ReceiveIntoStatementImpl extends CobolDivisionElementImpl implements ReceiveIntoStatement {

	protected Call communicationDescriptionCall;

	protected final ReceiveIntoStatementContext ctx;

	protected Call intoCall;

	protected NoData noData;

	protected ReceiveIntoType receiveIntoType;

	protected WithData withData;

	public ReceiveIntoStatementImpl(final ProgramUnit programUnit, final ReceiveIntoStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public NoData addNoData(final ReceiveNoDataContext ctx) {
		NoData result = (NoData) getASGElement(ctx);

		if (result == null) {
			result = new NoDataImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			noData = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public WithData addWithData(final ReceiveWithDataContext ctx) {
		WithData result = (WithData) getASGElement(ctx);

		if (result == null) {
			result = new WithDataImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			withData = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call getCommunicationDescriptionCall() {
		return communicationDescriptionCall;
	}

	@Override
	public Call getIntoCall() {
		return intoCall;
	}

	@Override
	public NoData getNoData() {
		return noData;
	}

	@Override
	public ReceiveIntoType getReceiveIntoType() {
		return receiveIntoType;
	}

	@Override
	public WithData getWithData() {
		return withData;
	}

	@Override
	public void setCommunicationDescriptionCall(final Call communicationDescriptionCall) {
		this.communicationDescriptionCall = communicationDescriptionCall;
	}

	@Override
	public void setIntoCall(final Call intoCall) {
		this.intoCall = intoCall;
	}

	@Override
	public void setReceiveIntoType(final ReceiveIntoType receiveIntoType) {
		this.receiveIntoType = receiveIntoType;
	}

}
