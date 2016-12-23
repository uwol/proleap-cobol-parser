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
import io.proleap.cobol.asg.metamodel.procedure.move.MoveCorrespondingTo;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveTo;

public class MoveStatementImpl extends StatementImpl implements MoveStatement {

	protected final MoveStatementContext ctx;

	protected MoveCorrespondingTo moveCorrespondingTo;

	protected MoveTo moveTo;

	protected final StatementType statementType = StatementTypeEnum.MOVE;

	protected Type type;

	public MoveStatementImpl(final ProgramUnit programUnit, final Scope scope, final MoveStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public MoveCorrespondingTo addMoveCorrespondingTo(final MoveCorrespondingToStatementContext ctx) {
		MoveCorrespondingTo result = (MoveCorrespondingTo) getASGElement(ctx);

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

			moveCorrespondingTo = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MoveTo addMoveTo(final MoveToStatementContext ctx) {
		MoveTo result = (MoveTo) getASGElement(ctx);

		if (result == null) {
			result = new MoveToImpl(programUnit, ctx);

			// sending area
			result.addSendingArea(ctx.moveToSendingArea());

			// receiving area calls
			for (final IdentifierContext identifierCtx : ctx.identifier()) {
				final Call receivingAreaCall = createCall(identifierCtx);
				result.addReceivingAreaCall(receivingAreaCall);
			}

			moveTo = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MoveCorrespondingTo getMoveCorrespondingTo() {
		return moveCorrespondingTo;
	}

	@Override
	public MoveTo getMoveTo() {
		return moveTo;
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
	public void setType(final Type type) {
		this.type = type;
	}

}
