/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.programlibrary;

import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureForClauseContext;
import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureGivingClauseContext;
import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureUsingClauseContext;
import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureWithClauseContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface ImportEntryProcedure extends CobolDivisionElement {

	ForClause addForClause(LibraryEntryProcedureForClauseContext ctx);

	GivingClause addGivingClause(LibraryEntryProcedureGivingClauseContext ctx);

	UsingClause addUsingClause(LibraryEntryProcedureUsingClauseContext ctx);

	WithClause addWithClause(LibraryEntryProcedureWithClauseContext ctx);

	ForClause getForClause();

	GivingClause getGivingClause();

	ValueStmt getProgramValueStmt();

	UsingClause getUsingClause();

	WithClause getWithClause();

	void setProgramValueStmt(ValueStmt programValueStmt);
}
