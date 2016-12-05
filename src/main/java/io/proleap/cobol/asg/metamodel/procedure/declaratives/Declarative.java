/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.declaratives;

import io.proleap.cobol.Cobol85Parser.ProcedureSectionHeaderContext;
import io.proleap.cobol.Cobol85Parser.UseStatementContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.procedure.use.UseStatement;

public interface Declarative extends CobolDivisionElement {

	SectionHeader addSectionHeader(ProcedureSectionHeaderContext ctx);

	UseStatement addUseStatement(UseStatementContext ctx);

	SectionHeader getSectionHeader();

	UseStatement getUseStament();
}
