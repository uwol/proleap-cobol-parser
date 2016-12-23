/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.RerunClauseContext;
import io.proleap.cobol.Cobol85Parser.RerunEveryClockContext;
import io.proleap.cobol.Cobol85Parser.RerunEveryOfContext;
import io.proleap.cobol.Cobol85Parser.RerunEveryRecordsContext;
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

	private final static Logger LOG = LogManager.getLogger(RerunClauseImpl.class);

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

			final IntegerLiteral integerLiteral = createIntegerLiteral(ctx.integerLiteral());
			result.setClockUnits(integerLiteral);

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
			final RerunEveryOf.Type type;

			if (ctx.REEL() != null) {
				type = RerunEveryOf.Type.REEL;
			} else if (ctx.UNIT() != null) {
				type = RerunEveryOf.Type.UNIT;
			} else {
				LOG.warn("unknown type {}.", ctx);
				type = null;
			}

			result.setType(type);

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

			final IntegerLiteral integerLiteral = createIntegerLiteral(ctx.integerLiteral());
			result.setRecords(integerLiteral);

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
