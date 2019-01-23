/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.accept.impl;

import io.proleap.cobol.CobolParser.AcceptFromDateStatementContext;
import io.proleap.cobol.CobolParser.AcceptFromEscapeKeyStatementContext;
import io.proleap.cobol.CobolParser.AcceptFromMnemonicStatementContext;
import io.proleap.cobol.CobolParser.AcceptMessageCountStatementContext;
import io.proleap.cobol.CobolParser.AcceptStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.OnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptFromDateStatement;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptFromDateStatement.DateType;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptFromEscapeKeyStatement;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptFromMnemonicStatement;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptMessageCountStatement;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class AcceptStatementImpl extends StatementImpl implements AcceptStatement {

	protected Call acceptCall;

	protected AcceptFromDateStatement acceptFromDateStatement;

	protected AcceptFromEscapeKeyStatement acceptFromEscapeKeyStatement;

	protected AcceptFromMnemonicStatement acceptFromMnemonicStatement;

	protected AcceptMessageCountStatement acceptMessageCountStatement;

	protected AcceptType acceptType;

	protected final AcceptStatementContext ctx;

	protected NotOnExceptionClause notOnExceptionClause;

	protected OnExceptionClause onExceptionClause;

	protected final StatementType statementType = StatementTypeEnum.ACCEPT;

	public AcceptStatementImpl(final ProgramUnit programUnit, final Scope scope, final AcceptStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public AcceptFromDateStatement addAcceptFromDateStatement(final AcceptFromDateStatementContext ctx) {
		AcceptFromDateStatement result = (AcceptFromDateStatement) getASGElement(ctx);

		if (result == null) {
			result = new AcceptFromDateImpl(programUnit, ctx);

			/*
			 * date type
			 */
			final AcceptFromDateStatement.DateType dateType;

			if (ctx.DATE() != null && ctx.YYYYMMDD() == null) {
				dateType = DateType.DATE;
			} else if (ctx.DATE() != null && ctx.YYYYMMDD() != null) {
				dateType = DateType.DATE_YYYYMMDD;
			} else if (ctx.DAY() != null && ctx.YYYYMMDD() != null) {
				dateType = DateType.DAY_YYYYMMDD;
			} else if (ctx.DAY() != null && ctx.YYYYDDD() != null) {
				dateType = DateType.YYYYDDD;
			} else if (ctx.DAY() != null && ctx.YYYYMMDD() == null) {
				dateType = DateType.DAY;
			} else if (ctx.TIME() != null) {
				dateType = DateType.TIME;
			} else if (ctx.TIMER() != null) {
				dateType = DateType.TIMER;
			} else if (ctx.TODAYS_DATE() != null && ctx.YYYYMMDD() == null) {
				dateType = DateType.TODAYS_DATE;
			} else if (ctx.TODAYS_DATE() != null && ctx.YYYYMMDD() != null) {
				dateType = DateType.TODAYS_DATE_MMDDYYYY;
			} else if (ctx.TODAYS_NAME() != null) {
				dateType = DateType.TODAYS_NAME;
			} else if (ctx.YEAR() != null) {
				dateType = DateType.YEAR;
			} else if (ctx.MMDDYYYY() != null) {
				dateType = DateType.MMDDYYYY;
			} else if (ctx.YYYYMMDD() != null) {
				dateType = DateType.YYYYMMDD;
			} else if (ctx.YYYYDDD() != null) {
				dateType = DateType.YYYYDDD;
			} else {
				dateType = null;
			}

			acceptFromDateStatement = result;
			result.setDateType(dateType);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AcceptFromEscapeKeyStatement addAcceptFromEscapeKeyStatement(final AcceptFromEscapeKeyStatementContext ctx) {
		AcceptFromEscapeKeyStatement result = (AcceptFromEscapeKeyStatement) getASGElement(ctx);

		if (result == null) {
			result = new AcceptFromEscapeKeyImpl(programUnit, ctx);

			acceptFromEscapeKeyStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AcceptFromMnemonicStatement addAcceptFromMnemonicStatement(final AcceptFromMnemonicStatementContext ctx) {
		AcceptFromMnemonicStatement result = (AcceptFromMnemonicStatement) getASGElement(ctx);

		if (result == null) {
			result = new AcceptFromMnemonicImpl(programUnit, ctx);

			final Call mnemonicCall = createCall(ctx.mnemonicName());
			result.setMnemonicCall(mnemonicCall);

			acceptFromMnemonicStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AcceptMessageCountStatement addAcceptMessageCountStatement(final AcceptMessageCountStatementContext ctx) {
		AcceptMessageCountStatement result = (AcceptMessageCountStatement) getASGElement(ctx);

		if (result == null) {
			result = new AcceptMessageCountImpl(programUnit, ctx);

			acceptMessageCountStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call getAcceptCall() {
		return acceptCall;
	}

	@Override
	public AcceptFromDateStatement getAcceptFromDateStatement() {
		return acceptFromDateStatement;
	}

	@Override
	public AcceptFromEscapeKeyStatement getAcceptFromEscapeKeyStatement() {
		return acceptFromEscapeKeyStatement;
	}

	@Override
	public AcceptFromMnemonicStatement getAcceptFromMnemonicStatement() {
		return acceptFromMnemonicStatement;
	}

	@Override
	public AcceptMessageCountStatement getAcceptMessageCountStatement() {
		return acceptMessageCountStatement;
	}

	@Override
	public AcceptType getAcceptType() {
		return acceptType;
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
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setAcceptCall(final Call acceptCall) {
		this.acceptCall = acceptCall;
	}

	@Override
	public void setAcceptType(final AcceptType acceptType) {
		this.acceptType = acceptType;
	}

	@Override
	public void setNotOnExceptionClause(final NotOnExceptionClause notOnExceptionClause) {
		this.notOnExceptionClause = notOnExceptionClause;
	}

	@Override
	public void setOnExceptionClause(final OnExceptionClause onExceptionClause) {
		this.onExceptionClause = onExceptionClause;
	}
}
