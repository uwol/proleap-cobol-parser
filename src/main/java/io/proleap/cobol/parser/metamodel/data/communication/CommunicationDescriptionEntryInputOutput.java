/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication;

import io.proleap.cobol.Cobol85Parser.EndKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.MessageDateClauseContext;
import io.proleap.cobol.Cobol85Parser.MessageTimeClauseContext;
import io.proleap.cobol.Cobol85Parser.StatusKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicTerminalClauseContext;
import io.proleap.cobol.Cobol85Parser.TextLengthClauseContext;

public interface CommunicationDescriptionEntryInputOutput extends CommunicationDescriptionEntry {

	EndKeyClause addEndKeyClause(EndKeyClauseContext ctx);

	MessageDateClause addMessageDateClause(MessageDateClauseContext ctx);

	MessageTimeClause addMessageTimeClause(MessageTimeClauseContext ctx);

	StatusKeyClause addStatusKeyClause(StatusKeyClauseContext ctx);

	SymbolicTerminalClause addSymbolicTerminalClause(SymbolicTerminalClauseContext ctx);

	TextLengthClause addTextLengthClause(TextLengthClauseContext ctx);

	EndKeyClause getEndKeyClause();

	MessageDateClause getMessageDateClause();

	MessageTimeClause getMessageTimeClause();

	StatusKeyClause getStatusKeyClause();

	SymbolicTerminalClause getSymbolicTerminalClause();

	TextLengthClause getTextLengthClause();
}
