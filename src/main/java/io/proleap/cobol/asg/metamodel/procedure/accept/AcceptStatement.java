/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.accept;

import io.proleap.cobol.Cobol85Parser.AcceptFromDateStatementContext;
import io.proleap.cobol.Cobol85Parser.AcceptFromMnemonicStatementContext;
import io.proleap.cobol.Cobol85Parser.AcceptMessageCountStatementContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Transfers low volume data or system date-related information into the
 * referenced data area.
 */
public interface AcceptStatement extends Statement {

	enum Type {
		Date, MessageCount, Mnemonic
	}

	AcceptFromDate addAcceptFromDate(AcceptFromDateStatementContext ctx);

	AcceptFromMnemonic addAcceptFromMnemonic(AcceptFromMnemonicStatementContext ctx);

	AcceptMessageCount addAcceptMessageCount(AcceptMessageCountStatementContext ctx);

	Call getAcceptCall();

	AcceptFromDate getAcceptFromDate();

	AcceptFromMnemonic getAcceptFromMnemonic();

	AcceptMessageCount getAcceptMessageCount();

	Type getType();

	void setAcceptCall(Call acceptCall);

	void setType(Type fromType);
}
