/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive.impl;

import io.proleap.cobol.CobolParser.ReceiveBeforeContext;
import io.proleap.cobol.CobolParser.ReceiveFromStatementContext;
import io.proleap.cobol.CobolParser.ReceiveIntoStatementContext;
import io.proleap.cobol.CobolParser.ReceiveSizeContext;
import io.proleap.cobol.CobolParser.ReceiveStatementContext;
import io.proleap.cobol.CobolParser.ReceiveStatusContext;
import io.proleap.cobol.CobolParser.ReceiveThreadContext;
import io.proleap.cobol.CobolParser.ReceiveWithContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.OnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.receive.ReceiveFromStatement;
import io.proleap.cobol.asg.metamodel.procedure.receive.ReceiveIntoStatement;
import io.proleap.cobol.asg.metamodel.procedure.receive.ReceiveStatement;

public class ReceiveStatementImpl extends StatementImpl implements ReceiveStatement {

	protected final ReceiveStatementContext ctx;

	protected NotOnExceptionClause notOnExceptionClause;

	protected OnExceptionClause onExceptionClause;

	protected ReceiveFromStatement receiveFromStatement;

	protected ReceiveIntoStatement receiveIntoStatement;

	protected ReceiveType receiveType;

	protected final StatementType statementType = StatementTypeEnum.RECEIVE;

	public ReceiveStatementImpl(final ProgramUnit programUnit, final Scope scope, final ReceiveStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public ReceiveFromStatement addReceiveFromStatement(final ReceiveFromStatementContext ctx) {
		ReceiveFromStatement result = (ReceiveFromStatement) getASGElement(ctx);

		if (result == null) {
			result = new ReceiveFromStatementImpl(programUnit, ctx);

			// data
			final Call dataCall = createCall(ctx.dataName());
			result.setDataCall(dataCall);

			// from
			result.addFrom(ctx.receiveFrom());

			// before
			for (final ReceiveBeforeContext receiveBeforeContext : ctx.receiveBefore()) {
				result.addBefore(receiveBeforeContext);
			}

			// with
			for (final ReceiveWithContext receiveWithContext : ctx.receiveWith()) {
				result.addWith(receiveWithContext);
			}

			// thread
			for (final ReceiveThreadContext receiveThreadContext : ctx.receiveThread()) {
				result.addThread(receiveThreadContext);
			}

			// size
			for (final ReceiveSizeContext receiveSizeContext : ctx.receiveSize()) {
				result.addSize(receiveSizeContext);
			}

			// status
			for (final ReceiveStatusContext receiveStatusContext : ctx.receiveStatus()) {
				result.addStatus(receiveStatusContext);
			}

			receiveFromStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ReceiveIntoStatement addReceiveIntoStatement(final ReceiveIntoStatementContext ctx) {
		ReceiveIntoStatement result = (ReceiveIntoStatement) getASGElement(ctx);

		if (result == null) {
			result = new ReceiveIntoStatementImpl(programUnit, ctx);

			// communication description entry
			final Call cdNameCall = createCall(ctx.cdName());
			result.setCommunicationDescriptionCall(cdNameCall);

			// type
			final ReceiveIntoStatement.ReceiveIntoType type;

			if (ctx.MESSAGE() != null) {
				type = ReceiveIntoStatement.ReceiveIntoType.MESSAGE;
			} else if (ctx.SEGMENT() != null) {
				type = ReceiveIntoStatement.ReceiveIntoType.SEGMENT;
			} else {
				type = null;
			}

			result.setReceiveIntoType(type);

			// into call
			if (ctx.identifier() != null) {
				final Call intoCall = createCall(ctx.identifier());
				result.setIntoCall(intoCall);
			}

			// no data
			if (ctx.receiveNoData() != null) {
				result.addNoData(ctx.receiveNoData());
			}

			// with data
			if (ctx.receiveWithData() != null) {
				result.addWithData(ctx.receiveWithData());
			}

			receiveIntoStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public NotOnExceptionClause getNotOnExceptionClause() {
		return notOnExceptionClause;
	}

	@Override
	public OnExceptionClause getOnExceptionClause() {
		return onExceptionClause;
	}

	@Override
	public ReceiveFromStatement getReceiveFromStatement() {
		return receiveFromStatement;
	}

	@Override
	public ReceiveIntoStatement getReceiveIntoStatement() {
		return receiveIntoStatement;
	}

	@Override
	public ReceiveType getReceiveType() {
		return receiveType;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setNotOnExceptionClause(final NotOnExceptionClause notOnExceptionClause) {
		this.notOnExceptionClause = notOnExceptionClause;
	}

	@Override
	public void setOnExceptionClause(final OnExceptionClause onExceptionClause) {
		this.onExceptionClause = onExceptionClause;
	}

	@Override
	public void setReceiveType(final ReceiveType receiveType) {
		this.receiveType = receiveType;
	}
}
