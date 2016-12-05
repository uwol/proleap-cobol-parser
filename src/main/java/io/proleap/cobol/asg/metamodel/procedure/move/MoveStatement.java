/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.move;

import io.proleap.cobol.Cobol85Parser.MoveCorrespondingToStatementContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Transfers data from one storage area to another.
 */
public interface MoveStatement extends Statement {

	enum Type {
		MoveCorrespondingTo, MoveTo
	}

	MoveCorrespondingTo addMoveCorrespondingTo(MoveCorrespondingToStatementContext ctx);

	MoveTo addMoveTo(MoveToStatementContext ctx);

	MoveCorrespondingTo getMoveCorrespondingTo();

	MoveTo getMoveTo();

	Type getType();

	void setType(Type type);

}
