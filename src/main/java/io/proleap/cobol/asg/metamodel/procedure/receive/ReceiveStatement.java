/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive;

import io.proleap.cobol.Cobol85Parser.ReceiveFromStatementContext;
import io.proleap.cobol.Cobol85Parser.ReceiveIntoStatementContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnException;
import io.proleap.cobol.asg.metamodel.procedure.OnException;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Obtains data from another program.
 */
public interface ReceiveStatement extends Statement {

	enum ReceiveType {
		FROM, INTO
	}

	ReceiveFromStatement addReceiveFromStatement(ReceiveFromStatementContext ctx);

	ReceiveIntoStatement addReceiveIntoStatement(ReceiveIntoStatementContext ctx);

	NotOnException getNotOnException();

	OnException getOnException();

	ReceiveFromStatement getReceiveFromStatement();

	ReceiveIntoStatement getReceiveIntoStatement();

	ReceiveType getReceiveType();

	void setNotOnException(NotOnException notOnException);

	void setOnException(OnException onException);

	void setReceiveType(ReceiveType receiveType);

}
