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
import io.proleap.cobol.parser.metamodel.procedure.NotOnException;
import io.proleap.cobol.parser.metamodel.procedure.OnException;
import io.proleap.cobol.parser.metamodel.procedure.OnOverflow;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Transfers control to another program.
 */
public interface CallStatement extends Statement {

	CallByContentStatement addCallByContentStatement(CallByContentStatementContext ctx);

	CallByReferenceStatement addCallByReferenceStatement(CallByReferenceStatementContext ctx);

	CallByValueStatement addCallByValueStatement(CallByValueStatementContext ctx);

	Giving addGiving(CallGivingPhraseContext ctx);

	List<CallByContentStatement> getCallByContentStatements();

	List<CallByReferenceStatement> getCallByReferenceStatements();

	List<CallByValueStatement> getCallByValueStatements();

	Giving getGiving();

	NotOnException getNotOnException();

	OnException getOnException();

	OnOverflow getOnOverflow();

	Call getProgramCall();

	void setNotOnException(NotOnException notOnExceptionClause);

	void setOnException(OnException onExceptionClause);

	void setOnOverflow(OnOverflow onOverflowPhrase);

	void setProgramCall(Call programCall);
}
