/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.string;

import java.util.List;

import io.proleap.cobol.CobolParser.StringIntoPhraseContext;
import io.proleap.cobol.CobolParser.StringSendingPhraseContext;
import io.proleap.cobol.CobolParser.StringWithPointerPhraseContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Merges contents of one or more data items into a single data item.
 */
public interface StringStatement extends Statement {

	IntoPhrase addIntoPhrase(StringIntoPhraseContext ctx);

	Sendings addSendings(StringSendingPhraseContext ctx);

	WithPointerPhrase addWithPointerPhrase(StringWithPointerPhraseContext ctx);

	IntoPhrase getIntoPhrase();

	NotOnOverflowPhrase getNotOnOverflowPhrase();

	OnOverflowPhrase getOnOverflowPhrase();

	List<Sendings> getSendings();

	WithPointerPhrase getWithPointerPhrase();

	void setNotOnOverflowPhrase(NotOnOverflowPhrase notOnOverflowPhrase);

	void setOnOverflowPhrase(OnOverflowPhrase onOverflowPhrase);
}
