/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.call;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.CallByContentStatementContext;
import io.proleap.cobol.Cobol85Parser.CallByReferenceStatementContext;
import io.proleap.cobol.Cobol85Parser.CallByValueStatementContext;
import io.proleap.cobol.Cobol85Parser.CallGivingPhraseContext;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.NotOnExceptionClause;
import io.proleap.cobol.parser.metamodel.procedure.OnExceptionClause;
import io.proleap.cobol.parser.metamodel.procedure.OnOverflowPhrase;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Transfers control to another program.
 */
public interface CallStatement extends Statement {

	CallByContentStatement addCallByContentStatement(CallByContentStatementContext ctx);

	CallByReferenceStatement addCallByReferenceStatement(CallByReferenceStatementContext ctx);

	CallByValueStatement addCallByValueStatement(CallByValueStatementContext ctx);

	GivingPhrase addGivingPhrase(CallGivingPhraseContext ctx);

	List<CallByContentStatement> getCallByContentStatements();

	List<CallByReferenceStatement> getCallByReferenceStatements();

	List<CallByValueStatement> getCallByValueStatements();

	GivingPhrase getGivingPhrase();

	NotOnExceptionClause getNotOnExceptionClause();

	OnExceptionClause getOnExceptionClause();

	OnOverflowPhrase getOnOverflowPhrase();

	Call getProgramCall();

	void setNotOnExceptionClause(NotOnExceptionClause notOnExceptionClause);

	void setOnExceptionClause(OnExceptionClause onExceptionClause);

	void setOnOverflowPhrase(OnOverflowPhrase onOverflowPhrase);

	void setProgramCall(Call programCall);
}
