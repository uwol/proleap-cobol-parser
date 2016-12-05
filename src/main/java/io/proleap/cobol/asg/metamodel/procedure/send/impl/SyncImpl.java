/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.send.impl;

import io.proleap.cobol.Cobol85Parser.SendAdvancingPhraseContext;
import io.proleap.cobol.Cobol85Parser.SendFromPhraseContext;
import io.proleap.cobol.Cobol85Parser.SendStatementSyncContext;
import io.proleap.cobol.Cobol85Parser.SendWithPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.send.Advancing;
import io.proleap.cobol.asg.metamodel.procedure.send.From;
import io.proleap.cobol.asg.metamodel.procedure.send.Sync;
import io.proleap.cobol.asg.metamodel.procedure.send.With;

public class SyncImpl extends CobolDivisionElementImpl implements Sync {

	protected Advancing advancing;

	protected SendStatementSyncContext ctx;

	protected From from;

	protected Call receivingProgramCall;

	protected boolean replacing;

	protected With with;

	public SyncImpl(final ProgramUnit programUnit, final SendStatementSyncContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Advancing addAdvancing(final SendAdvancingPhraseContext ctx) {
		Advancing result = (Advancing) getASGElement(ctx);

		if (result == null) {
			result = new AdvancingImpl(programUnit, ctx);

			// type
			final Advancing.Type type;

			if (ctx.sendAdvancingPage() != null) {
				type = Advancing.Type.Page;
			} else if (ctx.sendAdvancingLines() != null) {
				result.addAdvancingLines(ctx.sendAdvancingLines());
				type = Advancing.Type.Lines;
			} else if (ctx.sendAdvancingMnemonic() != null) {
				result.addAdvancingMnemonic(ctx.sendAdvancingMnemonic());
				type = Advancing.Type.Mnemonic;
			} else {
				type = null;
			}

			result.setType(type);

			// position type
			final Advancing.PositionType positionType;

			if (ctx.AFTER() != null) {
				positionType = Advancing.PositionType.After;
			} else if (ctx.BEFORE() != null) {
				positionType = Advancing.PositionType.Before;
			} else {
				positionType = null;
			}

			result.setPositionType(positionType);

			advancing = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public From addFrom(final SendFromPhraseContext ctx) {
		From result = (From) getASGElement(ctx);

		if (result == null) {
			result = new FromImpl(programUnit, ctx);

			// from
			final Call fromCall = createCall(ctx.identifier());
			result.setFromCall(fromCall);

			from = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public With addWith(final SendWithPhraseContext ctx) {
		With result = (With) getASGElement(ctx);

		if (result == null) {
			result = new WithImpl(programUnit, ctx);

			// type
			final With.Type type;

			if (ctx.identifier() != null) {
				final Call withCall = createCall(ctx.identifier());
				result.setWithCall(withCall);
				type = With.Type.Call;
			} else if (ctx.EGI() != null) {
				type = With.Type.Egi;
			} else if (ctx.EMI() != null) {
				type = With.Type.Emi;
			} else if (ctx.ESI() != null) {
				type = With.Type.Esi;
			} else {
				type = null;
			}

			result.setType(type);

			with = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Advancing getAdvancing() {
		return advancing;
	}

	@Override
	public From getFrom() {
		return from;
	}

	@Override
	public Call getReceivingProgramCall() {
		return receivingProgramCall;
	}

	@Override
	public boolean isReplacing() {
		return replacing;
	}

	@Override
	public With getWith() {
		return with;
	}

	@Override
	public void setReceivingProgramCall(final Call receivingProgramCall) {
		this.receivingProgramCall = receivingProgramCall;
	}

	@Override
	public void setReplacing(final boolean replacing) {
		this.replacing = replacing;
	}

}
