/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.start;

import io.proleap.cobol.CobolParser.StartKeyContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Positions in relative or indexed files for sequential reading of records.
 */
public interface StartStatement extends Statement {

	Key addKey(StartKeyContext ctx);

	Call getFileCall();

	InvalidKeyPhrase getInvalidKeyPhrase();

	Key getKey();

	NotInvalidKeyPhrase getNotInvalidKeyPhrase();

	void setFileCall(Call fileCall);

	void setInvalidKeyPhrase(InvalidKeyPhrase invalidKey);

	void setNotInvalidKeyPhrase(NotInvalidKeyPhrase notInvalidKeyPhrase);
}
