/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform;

import io.proleap.cobol.Cobol85Parser.PerformTestClauseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;

public interface Until extends CobolDivisionElement {

	TestClause addTestClause(PerformTestClauseContext ctx);

	ConditionValueStmt getCondition();

	TestClause getTestClause();

	void setCondition(ConditionValueStmt condition);

}
