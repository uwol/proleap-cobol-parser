/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.move.impl;

import io.proleap.cobol.CobolParser.IdentifierContext;
import io.proleap.cobol.CobolParser.MoveCorrespondingToStatementContext;
import io.proleap.cobol.CobolParser.MoveStatementContext;
import io.proleap.cobol.CobolParser.MoveToStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveCorrespondingToStatetement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveToStatement;

public class MoveStatementImpl extends StatementImpl implements MoveStatement {

	protected final MoveStatementContext ctx;

	protected MoveCorrespondingToStatetement moveCorrespondingToStatement;

	protected MoveToStatement moveToStatement;

	protected MoveType moveType;

	protected final StatementType statementType = StatementTypeEnum.MOVE;

	public MoveStatementImpl(final ProgramUnit programUnit, final Scope scope, final MoveStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public MoveCorrespondingToStatetement addMoveCorrespondingToStatement(
			final MoveCorrespondingToStatementContext ctx) {
		MoveCorrespondingToStatetement result = (MoveCorrespondingToStatetement) getASGElement(ctx);

		if (result == null) {
			result = new MoveCorrespondingToStatementImpl(programUnit, ctx);

			// sending area
			result.addMoveCorrespondingToSendingArea(ctx.moveCorrespondingToSendingArea());

			// receiving area calls
			for (final IdentifierContext identifierCtx : ctx.identifier()) {
				final Call receivingAreaCall = createCall(identifierCtx);
				result.addReceivingAreaCall(receivingAreaCall);
			}

			moveCorrespondingToStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MoveToStatement addMoveToStatement(final MoveToStatementContext ctx) {
		MoveToStatement result = (MoveToStatement) getASGElement(ctx);

		if (result == null) {
			result = new MoveToStatementImpl(programUnit, ctx);

			// sending area
			result.addMoveToSendingArea(ctx.moveToSendingArea());

			// receiving area calls
			for (final IdentifierContext identifierCtx : ctx.identifier()) {
				final Call receivingAreaCall = createCall(identifierCtx);
				result.addReceivingAreaCall(receivingAreaCall);
			}

			moveToStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MoveCorrespondingToStatetement getMoveCorrespondingToStatement() {
		return moveCorrespondingToStatement;
	}

	@Override
	public MoveToStatement getMoveToStatement() {
		return moveToStatement;
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
