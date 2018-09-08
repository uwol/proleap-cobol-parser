/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.rewrite;

import io.proleap.cobol.CobolParser.RewriteFromContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Logically replaces a record in a file.
 */
public interface RewriteStatement extends Statement {

	From addFrom(RewriteFromContext ctx);

	From getFrom();

	InvalidKeyPhrase getInvalidKeyPhrase();

	NotInvalidKeyPhrase getNotInvalidKeyPhrase();

	Call getRecordCall();

	void setInvalidKeyPhrase(InvalidKeyPhrase invalidKeyPhrase);

	void setNotInvalidKeyPhrase(NotInvalidKeyPhrase notInvalidKeyPhrase);

	void setRecordCall(Call recordCall);
}
