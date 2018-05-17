/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.string;

import java.util.List;

import io.proleap.cobol.CobolParser.StringDelimitedByPhraseContext;
import io.proleap.cobol.CobolParser.StringForPhraseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface Sendings extends CobolDivisionElement {

	enum SendingsType {
		DELIMITED_BY, FOR
	}

	DelimitedByPhrase addDelimitedByPhrase(StringDelimitedByPhraseContext ctx);

	ForPhrase addForPhrase(StringForPhraseContext ctx);

	void addSendingValueStmt(ValueStmt sendingValueStmt);

	DelimitedByPhrase getDelimitedByPhrase();

	ForPhrase getForPhrase();

	SendingsType getSendingsType();

	List<ValueStmt> getSendingValueStmts();

	void setSendingsType(SendingsType sendingsType);
}
