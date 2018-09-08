/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.ifstmt;

import io.proleap.cobol.CobolParser.IfElseContext;
import io.proleap.cobol.CobolParser.IfThenContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;

/**
 * Conditionally performs statements.
 */
public interface IfStatement extends Statement {

	Else addElse(IfElseContext ctx);

	Then addThen(IfThenContext ctx);

	ConditionValueStmt getCondition();

	Else getElse();

	Then getThen();

	void setCondition(ConditionValueStmt condition);
}
