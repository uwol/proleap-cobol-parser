/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.declaratives;

import io.proleap.cobol.CobolParser.ProcedureSectionHeaderContext;
import io.proleap.cobol.CobolParser.UseStatementContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.procedure.use.UseStatement;

public interface Declarative extends CobolDivisionElement {

	SectionHeader addSectionHeader(ProcedureSectionHeaderContext ctx);

	UseStatement addUseStatement(UseStatementContext ctx);

	SectionHeader getSectionHeader();

	UseStatement getUseStament();
}
