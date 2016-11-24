/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.initiate;

import java.util.List;

import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Initializes report writer control fields.
 */
public interface InitiateStatement extends Statement {

	void addReportCall(Call reportCall);

	List<Call> getReportCalls();
}
