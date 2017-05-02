/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform;

import io.proleap.cobol.Cobol85Parser.PerformTestClauseContext;
import io.proleap.cobol.Cobol85Parser.PerformVaryingClauseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface Varying extends CobolDivisionElement {

	TestClause addTestClause(PerformTestClauseContext ctx);

	VaryingClause addVaryingClause(PerformVaryingClauseContext ctx);

	TestClause getTestClause();

	VaryingClause getVaryingClause();

}
