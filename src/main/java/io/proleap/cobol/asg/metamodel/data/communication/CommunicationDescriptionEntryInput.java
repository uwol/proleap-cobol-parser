/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication;

import io.proleap.cobol.Cobol85Parser.EndKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.MessageCountClauseContext;
import io.proleap.cobol.Cobol85Parser.MessageDateClauseContext;
import io.proleap.cobol.Cobol85Parser.MessageTimeClauseContext;
import io.proleap.cobol.Cobol85Parser.StatusKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicQueueClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicSourceClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicSubQueueClauseContext;
import io.proleap.cobol.Cobol85Parser.TextLengthClauseContext;

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
