/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform;

import io.proleap.cobol.CobolParser.PerformTestClauseContext;
import io.proleap.cobol.CobolParser.PerformVaryingClauseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface Varying extends CobolDivisionElement {

	TestClause addTestClause(PerformTestClauseContext ctx);

	VaryingClause addVaryingClause(PerformVaryingClauseContext ctx);

	TestClause getTestClause();

	VaryingClause getVaryingClause();
}
