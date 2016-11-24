/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.purge;

import java.util.List;

import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Transfers records to the initial phase of a sort operation and writes records
 * to a sort file or a merge file.
 */
public interface PurgeStatement extends Statement {

	void addCommunicationDescriptionEntryCall(Call communicationDescriptionEntryCall);

	List<Call> getCommunicationDescriptionEntryCalls();
}
