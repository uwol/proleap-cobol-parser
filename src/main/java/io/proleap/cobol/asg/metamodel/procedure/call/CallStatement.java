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
import io.proleap.cobol.asg.metamodel.procedure.NotOnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.OnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

/**
 * Transfers control to another program.
 */
public interface CallStatement extends Statement {

	GivingPhrase addGivingPhrase(CallGivingPhraseContext ctx);

	UsingPhrase addUsingPhrase(CallUsingPhraseContext ctx);

	GivingPhrase getGivingPhrase();

	NotOnExceptionClause getNotOnExceptionClause();

	OnExceptionClause getOnExceptionClause();

	OnOverflowPhrase getOnOverflowPhrase();

	ValueStmt getProgramValueStmt();

	UsingPhrase getUsingPhrasePhrase();

	void setNotOnException(NotOnExceptionClause notOnExceptionClause);

	void setOnException(OnExceptionClause onExceptionClause);

	void setOnOverflow(OnOverflowPhrase onOverflowPhrase);

	void setProgramValueStmt(ValueStmt programValueStmt);
}
