/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.accept;

import io.proleap.cobol.Cobol85Parser.AcceptFromDateStatementContext;
import io.proleap.cobol.Cobol85Parser.AcceptFromEscapeKeyStatementContext;
import io.proleap.cobol.Cobol85Parser.AcceptFromMnemonicStatementContext;
import io.proleap.cobol.Cobol85Parser.AcceptMessageCountStatementContext;
import io.proleap.cobol.asg.metamodel.call.Call;
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

	void setAcceptCall(Call acceptCall);

	void setAcceptType(AcceptType acceptType);
}
