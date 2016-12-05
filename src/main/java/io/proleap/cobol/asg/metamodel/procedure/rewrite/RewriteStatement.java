/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.rewrite;

import io.proleap.cobol.Cobol85Parser.RewriteFromContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKey;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKey;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Logically replaces a record in a file.
 */
public interface RewriteStatement extends Statement {

	From addFrom(RewriteFromContext ctx);

	From getFrom();

	InvalidKey getInvalidKey();

	NotInvalidKey getNotInvalidKey();

	Call getRecordCall();

	void setInvalidKey(InvalidKey invalidKey);

	void setNotInvalidKey(NotInvalidKey notInvalidKey);

	void setRecordCall(Call recordCall);

}
