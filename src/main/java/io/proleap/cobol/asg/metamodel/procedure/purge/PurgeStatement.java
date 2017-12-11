/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.purge;

import java.util.List;

import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Eliminates a partial message from the Message Control System (MCS).
 */
public interface PurgeStatement extends Statement {

	void addCommunicationDescriptionEntryCall(Call communicationDescriptionEntryCall);

	List<Call> getCommunicationDescriptionEntryCalls();
}
