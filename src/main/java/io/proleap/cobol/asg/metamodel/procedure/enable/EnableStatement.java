/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.enable;

import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.disable.DisableStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

/**
 * Notifies the MCS to allow data transfer between specified output queues and
 * destinations (for output) or between specified sources and input queues (for
 * input).
 *
 * @see DisableStatement
 */
public interface EnableStatement extends Statement {

	enum EnableType {
		INPUT, INPUT_OUTPUT, OUTPUT
	}

	Call getCommunicationDescriptionCall();

	EnableType getEnableType();

	ValueStmt getKeyValueStmt();

	boolean isTerminal();

	void setCommunicationDescriptionCall(Call communicationDescriptionCall);

	void setEnableType(EnableType enableType);

	void setKeyValueStmt(ValueStmt keyValueStmt);

	void setTerminal(boolean terminal);
}
