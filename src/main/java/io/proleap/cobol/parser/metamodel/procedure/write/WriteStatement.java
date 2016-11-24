/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.write;

import io.proleap.cobol.Cobol85Parser.WriteAdvancingPhraseContext;
import io.proleap.cobol.Cobol85Parser.WriteAtEndOfPagePhraseContext;
import io.proleap.cobol.Cobol85Parser.WriteFromPhraseContext;
import io.proleap.cobol.Cobol85Parser.WriteNotAtEndOfPagePhraseContext;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.InvalidKey;
import io.proleap.cobol.parser.metamodel.procedure.NotInvalidKey;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Releases a logical record for a file.
 */
public interface WriteStatement extends Statement {

	Advancing addAdvancing(WriteAdvancingPhraseContext ctx);

	AtEndOfPage addAtEndOfPage(WriteAtEndOfPagePhraseContext ctx);

	From addFrom(WriteFromPhraseContext ctx);

	NotAtEndOfPage addNotAtEndOfPage(WriteNotAtEndOfPagePhraseContext ctx);

	Advancing getAdvancing();

	AtEndOfPage getAtEndOfPage();

	From getFrom();

	InvalidKey getInvalidKey();

	NotAtEndOfPage getNotAtEndOfPage();

	NotInvalidKey getNotInvalidKey();

	Call getRecordCall();

	void setInvalidKey(InvalidKey invalidKey);

	void setNotInvalidKey(NotInvalidKey notInvalidKey);

	void setRecordCall(Call recordCall);

}
