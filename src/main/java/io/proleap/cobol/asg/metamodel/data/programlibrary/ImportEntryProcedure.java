/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary;

import io.proleap.cobol.CobolParser.LibraryEntryProcedureForClauseContext;
import io.proleap.cobol.CobolParser.LibraryEntryProcedureGivingClauseContext;
import io.proleap.cobol.CobolParser.LibraryEntryProcedureUsingClauseContext;
import io.proleap.cobol.CobolParser.LibraryEntryProcedureWithClauseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface ImportEntryProcedure extends CobolDivisionElement {

	ForClause addForClause(LibraryEntryProcedureForClauseContext ctx);

	GivingClause addGivingClause(LibraryEntryProcedureGivingClauseContext ctx);

	UsingClause addUsingClause(LibraryEntryProcedureUsingClauseContext ctx);

	WithClause addWithClause(LibraryEntryProcedureWithClauseContext ctx);

	ForClause getForClause();

	GivingClause getGivingClause();

	Call getProgramCall();

	UsingClause getUsingClause();

	WithClause getWithClause();

	void setProgramCall(Call programCall);
}
