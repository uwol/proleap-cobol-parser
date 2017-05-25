/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.move.impl;

import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.MoveCorrespondingToStatementContext;
import io.proleap.cobol.Cobol85Parser.MoveStatementContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveCorrespondingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveToPhrase;

public class MoveStatementImpl extends StatementImpl implements MoveStatement {

	protected final MoveStatementContext ctx;

	protected MoveCorrespondingPhrase moveCorrespondingPhrase;

	protected MoveToPhrase moveToPhrase;

	protected MoveType moveType;

	protected final StatementType statementType = StatementTypeEnum.MOVE;

	public MoveStatementImpl(final ProgramUnit programUnit, final Scope scope, final MoveStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public MoveCorrespondingPhrase addMoveCorrespondingPhrase(final MoveCorrespondingToStatementContext ctx) {
		MoveCorrespondingPhrase result = (MoveCorrespondingPhrase) getASGElement(ctx);

		if (result == null) {
			result = new MoveCorrespondingToImpl(programUnit, ctx);

			// sending
			final Call sendingCall = createCall(ctx.qualifiedDataName());
			result.setSendingCall(sendingCall);

			// receiving area calls
			for (final IdentifierContext identifierCtx : ctx.identifier()) {
				final Call receivingAreaCall = createCall(identifierCtx);
				result.addReceivingAreaCall(receivingAreaCall);
			}

			moveCorrespondingPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MoveToPhrase addMoveTo(final MoveToStatementContext ctx) {
		MoveToPhrase result = (MoveToPhrase) getASGElement(ctx);

		if (result == null) {
			result = new MoveToPhraseImpl(programUnit, ctx);

			// sending area
			result.addSendingArea(ctx.moveToSendingArea());

			// receiving area calls
			for (final IdentifierContext identifierCtx : ctx.identifier()) {
				final Call receivingAreaCall = createCall(identifierCtx);
				result.addReceivingAreaCall(receivingAreaCall);
			}

			moveToPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MoveCorrespondingPhrase getMoveCorrespondingPhrase() {
		return moveCorrespondingPhrase;
	}

	@Override
	public MoveToPhrase getMoveTo() {
		return moveToPhrase;
	}

	@Override
	public MoveType getMoveType() {
		return moveType;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setMoveType(final MoveType moveType) {
		this.moveType = moveType;
	}

}
