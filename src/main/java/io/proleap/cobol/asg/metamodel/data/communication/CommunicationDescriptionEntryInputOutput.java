/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication;

import io.proleap.cobol.CobolParser.EndKeyClauseContext;
import io.proleap.cobol.CobolParser.MessageDateClauseContext;
import io.proleap.cobol.CobolParser.MessageTimeClauseContext;
import io.proleap.cobol.CobolParser.StatusKeyClauseContext;
import io.proleap.cobol.CobolParser.SymbolicTerminalClauseContext;
import io.proleap.cobol.CobolParser.TextLengthClauseContext;

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
