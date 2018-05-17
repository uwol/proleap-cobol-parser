/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication;

import io.proleap.cobol.CobolParser.EndKeyClauseContext;
import io.proleap.cobol.CobolParser.MessageCountClauseContext;
import io.proleap.cobol.CobolParser.MessageDateClauseContext;
import io.proleap.cobol.CobolParser.MessageTimeClauseContext;
import io.proleap.cobol.CobolParser.StatusKeyClauseContext;
import io.proleap.cobol.CobolParser.SymbolicQueueClauseContext;
import io.proleap.cobol.CobolParser.SymbolicSourceClauseContext;
import io.proleap.cobol.CobolParser.SymbolicSubQueueClauseContext;
import io.proleap.cobol.CobolParser.TextLengthClauseContext;

public interface CommunicationDescriptionEntryInput extends CommunicationDescriptionEntry {

	EndKeyClause addEndKeyClause(EndKeyClauseContext ctx);

	MessageCountClause addMessageCountClause(MessageCountClauseContext ctx);

	MessageDateClause addMessageDateClause(MessageDateClauseContext ctx);

	MessageTimeClause addMessageTimeClause(MessageTimeClauseContext ctx);

	StatusKeyClause addStatusKeyClause(StatusKeyClauseContext ctx);

	SymbolicQueueClause addSymbolicQueueClause(SymbolicQueueClauseContext ctx);

	SymbolicSourceClause addSymbolicSourceClause(SymbolicSourceClauseContext ctx);

	SymbolicSubQueueClause addSymbolicSubQueueClause(SymbolicSubQueueClauseContext ctx);

	TextLengthClause addTextLengthClause(TextLengthClauseContext ctx);

	EndKeyClause getEndKeyClause();

	MessageCountClause getMessageCountClause();

	MessageDateClause getMessageDateClause();

	MessageTimeClause getMessageTimeClause();

	StatusKeyClause getStatusKeyClause();

	SymbolicQueueClause getSymbolicQueueClause();

	SymbolicSourceClause getSymbolicSourceClause();

	SymbolicSubQueueClause getSymbolicSubQueueClause();

	TextLengthClause getTextLengthClause();
}
