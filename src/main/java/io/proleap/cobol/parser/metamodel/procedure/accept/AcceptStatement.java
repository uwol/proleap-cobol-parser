/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.accept;

import io.proleap.cobol.Cobol85Parser.AcceptFromDateContext;
import io.proleap.cobol.Cobol85Parser.AcceptFromMnemonicContext;
import io.proleap.cobol.Cobol85Parser.AcceptMessageCountContext;
import io.proleap.cobol.parser.metamodel.procedure.Statement;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface AcceptStatement extends Statement {

	enum Type {
		Date, MessageCount, Mnemonic
	}

	AcceptFromDate addAcceptFromDate(AcceptFromDateContext ctx);

	AcceptFromMnemonic addAcceptFromMnemonic(AcceptFromMnemonicContext ctx);

	AcceptMessageCount addAcceptMessageCount(AcceptMessageCountContext ctx);

	AcceptFromDate getAcceptFromDate();

	AcceptFromMnemonic getAcceptFromMnemonic();

	AcceptMessageCount getAcceptMessageCount();

	ValueStmt getAcceptValueStmt();

	Type getType();

	void setAcceptValueStmt(ValueStmt acceptValueStmt);

	void setType(Type fromType);
}
