/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.CobolParser.RerunClauseContext;
import io.proleap.cobol.CobolParser.RerunEveryClockContext;
import io.proleap.cobol.CobolParser.RerunEveryOfContext;
import io.proleap.cobol.CobolParser.RerunEveryRecordsContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.RerunClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.RerunEveryClock;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.RerunEveryOf;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.RerunEveryRecords;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class RerunClauseImpl extends CobolDivisionElementImpl implements RerunClause {

	private final static Logger LOG = LoggerFactory.getLogger(RerunClauseImpl.class);

	protected final RerunClauseContext ctx;

	protected ValueStmt onValueStmt;

	protected RerunEveryClock rerunEveryClock;

	protected RerunEveryOf rerunEveryOf;

	protected RerunEveryRecords rerunEveryRecords;

	public RerunClauseImpl(final ProgramUnit programUnit, final RerunClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public RerunEveryClock addRerunEveryClock(final RerunEveryClockContext ctx) {
		RerunEveryClock result = (RerunEveryClock) getASGElement(ctx);

		if (result == null) {
			result = new RerunEveryClockImpl(programUnit, ctx);

			if (ctx.integerLiteral() != null) {
				final IntegerLiteral integerLiteral = createIntegerLiteral(ctx.integerLiteral());
				result.setClockUnits(integerLiteral);
			}

			rerunEveryClock = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public RerunEveryOf addRerunEveryOf(final RerunEveryOfContext ctx) {
		RerunEveryOf result = (RerunEveryOf) getASGElement(ctx);

		if (result == null) {
			result = new RerunEveryOfImpl(programUnit, ctx);

			/*
			 * type
			 */
			final RerunEveryOf.RerunEveryOfType type;

			if (ctx.REEL() != null) {
				type = RerunEveryOf.RerunEveryOfType.REEL;
			} else if (ctx.UNIT() != null) {
				type = RerunEveryOf.RerunEveryOfType.UNIT;
			} else {
				LOG.warn("unknown type {}.", ctx);
				type = null;
			}

			result.setRerunEveryOfType(type);

			/*
			 * file name
			 */
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			rerunEveryOf = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public RerunEveryRecords addRerunEveryRecords(final RerunEveryRecordsContext ctx) {
		RerunEveryRecords result = (RerunEveryRecords) getASGElement(ctx);

		if (result == null) {
			result = new RerunEveryRecordsImpl(programUnit, ctx);

			if (ctx.integerLiteral() != null) {
				final IntegerLiteral integerLiteral = createIntegerLiteral(ctx.integerLiteral());
				result.setRecords(integerLiteral);
			}

			rerunEveryRecords = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt getOnValueStmt() {
		return onValueStmt;
	}

	@Override
	public RerunEveryClock getRerunEveryClock() {
		return rerunEveryClock;
	}

	@Override
	public RerunEveryOf getRerunEveryOf() {
		return rerunEveryOf;
	}

	@Override
	public RerunEveryRecords getRerunEveryRecords() {
		return rerunEveryRecords;
	}

	@Override
	public void setOnValueStmt(final ValueStmt onValueStmt) {
		this.onValueStmt = onValueStmt;
	}

}
