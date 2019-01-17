/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call;

import io.proleap.cobol.CobolParser.CallGivingPhraseContext;
import io.proleap.cobol.CobolParser.CallUsingPhraseContext;
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

	UsingPhrase getUsingPhrase();

	void setNotOnExceptionClause(NotOnExceptionClause notOnExceptionClause);

	void setOnExceptionClause(OnExceptionClause onExceptionClause);

	void setOnOverflowPhrase(OnOverflowPhrase onOverflowPhrase);

	void setProgramValueStmt(ValueStmt programValueStmt);
}
