/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call;

import io.proleap.cobol.Cobol85Parser.CallGivingPhraseContext;
import io.proleap.cobol.Cobol85Parser.CallUsingPhraseContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnException;
import io.proleap.cobol.asg.metamodel.procedure.OnException;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflow;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

/**
 * Transfers control to another program.
 */
public interface CallStatement extends Statement {

	Giving addGiving(CallGivingPhraseContext ctx);

	Using addUsing(CallUsingPhraseContext ctx);

	Giving getGiving();

	NotOnException getNotOnException();

	OnException getOnException();

	OnOverflow getOnOverflow();

	ValueStmt getProgramValueStmt();

	Using getUsing();

	void setNotOnException(NotOnException notOnExceptionClause);

	void setOnException(OnException onExceptionClause);

	void setOnOverflow(OnOverflow onOverflowPhrase);

	void setProgramValueStmt(ValueStmt programValueStmt);
}
