/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication;

import io.proleap.cobol.Cobol85Parser.DestinationCountClauseContext;
import io.proleap.cobol.Cobol85Parser.DestinationTableClauseContext;
import io.proleap.cobol.Cobol85Parser.ErrorKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.StatusKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicDestinationClauseContext;
import io.proleap.cobol.Cobol85Parser.TextLengthClauseContext;

public interface CommunicationDescriptionEntryOutput extends CommunicationDescriptionEntry {

	DestinationCountClause addDestinationCountClause(DestinationCountClauseContext ctx);

	DestinationTableClause addDestinationTableClause(DestinationTableClauseContext ctx);

	ErrorKeyClause addErrorKeyClause(ErrorKeyClauseContext ctx);

	StatusKeyClause addStatusKeyClause(StatusKeyClauseContext ctx);

	SymbolicDestinationClause addSymbolicDestinationClause(SymbolicDestinationClauseContext ctx);

	TextLengthClause addTextLengthClause(TextLengthClauseContext ctx);

	DestinationCountClause getDestinationCountClause();

	DestinationTableClause getDestinationTableClause();

	ErrorKeyClause getErrorKeyClause();

	StatusKeyClause getStatusKeyClause();

	SymbolicDestinationClause getSymbolicDestinationClause();

	TextLengthClause getTextLengthClause();
}
