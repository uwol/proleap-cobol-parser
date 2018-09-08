/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.returnstmt;

import io.proleap.cobol.CobolParser.ReturnIntoContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.AtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotAtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Obtains sorted or merged records from SORT or MERGE.
 */
public interface ReturnStatement extends Statement {

	void addFileCall(Call fileCall);

	Into addInto(ReturnIntoContext ctx);

	AtEndPhrase getAtEndPhrase();

	Call getFileCall();

	Into getInto();

	NotAtEndPhrase getNotAtEndPhrase();

	void setAtEndPhrase(AtEndPhrase atEndPhrase);

	void setNotAtEndPhrase(NotAtEndPhrase notAtEndPhrase);
}
