/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.receive.impl;

import io.proleap.cobol.Cobol85Parser.ReceiveIntoStatementContext;
import io.proleap.cobol.Cobol85Parser.ReceiveNoDataContext;
import io.proleap.cobol.Cobol85Parser.ReceiveWithDataContext;
import io.proleap.cobol.Cobol85Parser.StatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.receive.NoData;
import io.proleap.cobol.parser.metamodel.procedure.receive.ReceiveIntoStatement;
import io.proleap.cobol.parser.metamodel.procedure.receive.WithData;

public class ReceiveIntoStatementImpl extends CobolDivisionElementImpl implements ReceiveIntoStatement {

	protected Call communicationDescriptionCall;

	protected final ReceiveIntoStatementContext ctx;

	protected Call intoCall;

	protected NoData noData;

	protected Type type;

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
	public Type getType() {
		return type;
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
	public void setType(final Type type) {
		this.type = type;
	}

}
