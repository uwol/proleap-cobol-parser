/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.ifstmt;

import io.proleap.cobol.Cobol85Parser.IfElseContext;
import io.proleap.cobol.Cobol85Parser.IfThenContext;
import io.proleap.cobol.parser.metamodel.procedure.Statement;
import io.proleap.cobol.parser.metamodel.valuestmt.ConditionValueStmt;

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
