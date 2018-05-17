/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.accept;

import io.proleap.cobol.CobolParser.AcceptFromDateStatementContext;
import io.proleap.cobol.CobolParser.AcceptFromEscapeKeyStatementContext;
import io.proleap.cobol.CobolParser.AcceptFromMnemonicStatementContext;
import io.proleap.cobol.CobolParser.AcceptMessageCountStatementContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.OnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Transfers low volume data or system date-related information into the
 * referenced data area.
 */
public interface AcceptStatement extends Statement {

	enum AcceptType {
		DATE, FROM_ESCAPE_KEY, MESSAGE_COUNT, MNEMONIC, NO_FROM
	}

	AcceptFromDateStatement addAcceptFromDateStatement(AcceptFromDateStatementContext ctx);

	AcceptFromEscapeKeyStatement addAcceptFromEscapeKeyStatement(AcceptFromEscapeKeyStatementContext ctx);

	AcceptFromMnemonicStatement addAcceptFromMnemonicStatement(AcceptFromMnemonicStatementContext ctx);

	AcceptMessageCountStatement addAcceptMessageCountStatement(AcceptMessageCountStatementContext ctx);

	Call getAcceptCall();

	AcceptFromDateStatement getAcceptFromDateStatement();

	AcceptFromEscapeKeyStatement getAcceptFromEscapeKeyStatement();

	AcceptFromMnemonicStatement getAcceptFromMnemonicStatement();

	AcceptMessageCountStatement getAcceptMessageCountStatement();

	AcceptType getAcceptType();

	NotOnExceptionClause getNotOnExceptionClause();

	OnExceptionClause getOnExceptionClause();

	void setAcceptCall(Call acceptCall);

	void setAcceptType(AcceptType acceptType);

	void setNotOnExceptionClause(NotOnExceptionClause notOnExceptionClause);

	void setOnExceptionClause(OnExceptionClause onExceptionClause);
}
