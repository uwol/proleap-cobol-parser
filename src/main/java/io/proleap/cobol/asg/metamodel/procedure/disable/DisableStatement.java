/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.disable;

import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.enable.EnableStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

/**
 * Notifies the MCS to inhibit data transfer between specified output queues and
 * destinations (for output) or between specified sources and input queues (for
 * input).
 *
 * @see EnableStatement
 */
public interface DisableStatement extends Statement {

	enum DisableType {
		INPUT, INPUT_OUTPUT, OUTPUT
	}

	Call getCommunicationDescriptionCall();

	DisableType getDisableType();

	ValueStmt getKeyValueStmt();

	boolean isTerminal();

	void setCommunicationDescriptionCall(Call communicationDescriptionCall);

	void setDisableType(DisableType disableType);

	void setKeyValueStmt(ValueStmt keyValueStmt);

	void setTerminal(boolean terminal);
}
