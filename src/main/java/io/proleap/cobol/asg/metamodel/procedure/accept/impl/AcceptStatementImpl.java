/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.accept.impl;

import io.proleap.cobol.Cobol85Parser.AcceptFromDateStatementContext;
import io.proleap.cobol.Cobol85Parser.AcceptFromEscapeKeyStatementContext;
import io.proleap.cobol.Cobol85Parser.AcceptFromMnemonicStatementContext;
import io.proleap.cobol.Cobol85Parser.AcceptMessageCountStatementContext;
import io.proleap.cobol.Cobol85Parser.AcceptStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptFromDate;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptFromEscapeKey;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptFromMnemonic;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptMessageCount;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class AcceptStatementImpl extends StatementImpl implements AcceptStatement {

	protected Call acceptCall;

	protected AcceptFromDate acceptFromDate;

	protected AcceptFromEscapeKey acceptFromEscapeKey;

	protected AcceptFromMnemonic acceptFromMnemonic;

	protected AcceptMessageCount acceptMessageCount;

	protected final AcceptStatementContext ctx;

	protected final StatementType statementType = StatementTypeEnum.Accept;

	protected Type type;

	public AcceptStatementImpl(final ProgramUnit programUnit, final Scope scope, final AcceptStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public AcceptFromDate addAcceptFromDate(final AcceptFromDateStatementContext ctx) {
		AcceptFromDate result = (AcceptFromDate) getASGElement(ctx);

		if (result == null) {
			result = new AcceptFromDateImpl(programUnit, ctx);

			/*
			 * date type
			 */
			final AcceptFromDate.DateType dateType;

			if (ctx.DATE() != null && ctx.YYYYMMDD() == null) {
				dateType = AcceptFromDate.DateType.Date;
			} else if (ctx.DATE() != null && ctx.YYYYMMDD() != null) {
				dateType = AcceptFromDate.DateType.DateYyyyMmDd;
			} else if (ctx.DAY() != null && ctx.YYYYMMDD() == null) {
				dateType = AcceptFromDate.DateType.Day;
			} else if (ctx.DAY() != null && ctx.YYYYMMDD() != null) {
				dateType = AcceptFromDate.DateType.DayYyyyMmDd;
			} else if (ctx.TIME() != null) {
				dateType = AcceptFromDate.DateType.Time;
			} else if (ctx.TIMER() != null) {
				dateType = AcceptFromDate.DateType.Timer;
			} else if (ctx.TODAYS_DATE() != null && ctx.YYYYMMDD() == null) {
				dateType = AcceptFromDate.DateType.TodaysDate;
			} else if (ctx.TODAYS_DATE() != null && ctx.YYYYMMDD() != null) {
				dateType = AcceptFromDate.DateType.TodaysDateMmDdYyyy;
			} else if (ctx.TODAYS_NAME() != null) {
				dateType = AcceptFromDate.DateType.TodaysName;
			} else if (ctx.YEAR() != null) {
				dateType = AcceptFromDate.DateType.Year;
			} else if (ctx.YYYYDDD() != null) {
				dateType = AcceptFromDate.DateType.YyyyDdd;
			} else if (ctx.MMDDYYYY() != null) {
				dateType = AcceptFromDate.DateType.YyyyMmDd;
			} else {
				dateType = null;
			}

			acceptFromDate = result;
			result.setDateType(dateType);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AcceptFromEscapeKey addAcceptFromEscapeKey(final AcceptFromEscapeKeyStatementContext ctx) {
		AcceptFromEscapeKey result = (AcceptFromEscapeKey) getASGElement(ctx);

		if (result == null) {
			result = new AcceptFromEscapeKeyImpl(programUnit, ctx);

			acceptFromEscapeKey = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AcceptFromMnemonic addAcceptFromMnemonic(final AcceptFromMnemonicStatementContext ctx) {
		AcceptFromMnemonic result = (AcceptFromMnemonic) getASGElement(ctx);

		if (result == null) {
			result = new AcceptFromMnemonicImpl(programUnit, ctx);

			final Call mnemonicCall = createCall(ctx.mnemonicName());
			result.setMnemonicCall(mnemonicCall);

			acceptFromMnemonic = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AcceptMessageCount addAcceptMessageCount(final AcceptMessageCountStatementContext ctx) {
		AcceptMessageCount result = (AcceptMessageCount) getASGElement(ctx);

		if (result == null) {
			result = new AcceptMessageCountImpl(programUnit, ctx);

			acceptMessageCount = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call getAcceptCall() {
		return acceptCall;
	}

	@Override
	public AcceptFromDate getAcceptFromDate() {
		return acceptFromDate;
	}

	@Override
	public AcceptFromEscapeKey getAcceptFromEscapeKey() {
		return acceptFromEscapeKey;
	}

	@Override
	public AcceptFromMnemonic getAcceptFromMnemonic() {
		return acceptFromMnemonic;
	}

	@Override
	public AcceptMessageCount getAcceptMessageCount() {
		return acceptMessageCount;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setAcceptCall(final Call acceptCall) {
		this.acceptCall = acceptCall;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
