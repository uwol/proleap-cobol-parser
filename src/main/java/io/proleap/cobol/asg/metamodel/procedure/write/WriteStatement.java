/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.write;

import io.proleap.cobol.Cobol85Parser.WriteAdvancingPhraseContext;
import io.proleap.cobol.Cobol85Parser.WriteAtEndOfPagePhraseContext;
import io.proleap.cobol.Cobol85Parser.WriteFromPhraseContext;
import io.proleap.cobol.Cobol85Parser.WriteNotAtEndOfPagePhraseContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Releases a logical record for a file.
 */
public interface WriteStatement extends Statement {

	AdvancingPhrase addAdvancingPhrase(WriteAdvancingPhraseContext ctx);

	AtEndOfPagePhrase addAtEndOfPagePhrase(WriteAtEndOfPagePhraseContext ctx);

	From addFrom(WriteFromPhraseContext ctx);

	NotAtEndOfPagePhrase addNotAtEndOfPagePhrase(WriteNotAtEndOfPagePhraseContext ctx);

	AdvancingPhrase getAdvancingPhrase();

	AtEndOfPagePhrase getAtEndOfPagePhrase();

	From getFrom();

	InvalidKeyPhrase getInvalidKeyPhrase();

	NotAtEndOfPagePhrase getNotAtEndOfPagePhrase();

	NotInvalidKeyPhrase getNotInvalidKeyPhrase();

	Call getRecordCall();

	void setInvalidKeyPhrase(InvalidKeyPhrase invalidKeyPhrase);

	void setNotInvalidKeyPhrase(NotInvalidKeyPhrase notInvalidKeyPhrase);

	void setRecordCall(Call recordCall);

}
