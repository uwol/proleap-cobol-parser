/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.write;

import io.proleap.cobol.CobolParser.WriteAdvancingPhraseContext;
import io.proleap.cobol.CobolParser.WriteFromPhraseContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Releases a logical record for a file.
 */
public interface WriteStatement extends Statement {

	AdvancingPhrase addAdvancingPhrase(WriteAdvancingPhraseContext ctx);

	From addFrom(WriteFromPhraseContext ctx);

	AdvancingPhrase getAdvancingPhrase();

	AtEndOfPagePhrase getAtEndOfPagePhrase();

	From getFrom();

	InvalidKeyPhrase getInvalidKeyPhrase();

	NotAtEndOfPagePhrase getNotAtEndOfPagePhrase();

	NotInvalidKeyPhrase getNotInvalidKeyPhrase();

	Call getRecordCall();

	void setAtEndOfPagePhrase(AtEndOfPagePhrase atEndOfPagePhrase);

	void setInvalidKeyPhrase(InvalidKeyPhrase invalidKeyPhrase);

	void setNotAtEndOfPagePhrase(NotAtEndOfPagePhrase notAtEndOfPagePhrase);

	void setNotInvalidKeyPhrase(NotInvalidKeyPhrase notInvalidKeyPhrase);

	void setRecordCall(Call recordCall);
}
