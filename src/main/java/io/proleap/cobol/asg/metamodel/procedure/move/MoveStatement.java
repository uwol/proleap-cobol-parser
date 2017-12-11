/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.move;

import io.proleap.cobol.Cobol85Parser.MoveCorrespondingToStatementContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Transfers data from one storage area to another.
 */
public interface MoveStatement extends Statement {

	enum MoveType {
		MOVE_CORRESPONDING, MOVE_TO
	}

	MoveCorrespondingPhrase addMoveCorrespondingPhrase(MoveCorrespondingToStatementContext ctx);

	MoveToPhrase addMoveToPhrase(MoveToStatementContext ctx);

	MoveCorrespondingPhrase getMoveCorrespondingPhrase();

	MoveToPhrase getMoveToPhrase();

	MoveType getMoveType();

	void setMoveType(MoveType moveType);

}
