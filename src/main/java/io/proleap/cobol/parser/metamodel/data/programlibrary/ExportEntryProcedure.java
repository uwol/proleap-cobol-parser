/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.programlibrary;

import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureForClauseContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface ExportEntryProcedure extends CobolDivisionElement {

	ForClause addForClause(LibraryEntryProcedureForClauseContext ctx);

	ForClause getForClause();

	Call getProgramCall();

	void setProgramCall(Call programCall);

}
