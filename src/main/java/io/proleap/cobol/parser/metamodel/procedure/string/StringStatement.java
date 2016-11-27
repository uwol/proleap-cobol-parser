/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.string;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.StringIntoPhraseContext;
import io.proleap.cobol.Cobol85Parser.StringSendingPhraseContext;
import io.proleap.cobol.Cobol85Parser.StringWithPointerPhraseContext;
import io.proleap.cobol.parser.metamodel.procedure.NotOnOverflow;
import io.proleap.cobol.parser.metamodel.procedure.OnOverflow;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Merges contents of one or more data items into a single data item.
 */
public interface StringStatement extends Statement {

	Into addInto(StringIntoPhraseContext ctx);

	Sending addSending(StringSendingPhraseContext ctx);

	WithPointer addWithPointer(StringWithPointerPhraseContext ctx);

	Into getInto();

	NotOnOverflow getNotOnOverflow();

	OnOverflow getOnOverflow();

	List<Sending> getSendings();

	WithPointer getWithPointer();

	void setNotOnOverflow(NotOnOverflow notOnOverflow);

	void setOnOverflow(OnOverflow onOverflow);
}
