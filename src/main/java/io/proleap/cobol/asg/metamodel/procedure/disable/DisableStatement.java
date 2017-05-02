/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
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

	enum Type {
		INPUT, INPUT_OUTPUT, OUTPUT
	}

	Call getCommunicationDescriptionCall();

	ValueStmt getKeyValueStmt();

	Type getType();

	boolean isTerminal();

	void setCommunicationDescriptionCall(Call communicationDescriptionCall);

	void setKeyValueStmt(ValueStmt keyValueStmt);

	void setTerminal(boolean terminal);

	void setType(Type type);

}
