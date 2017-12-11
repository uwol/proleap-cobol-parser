/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.use;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface DebugOn extends CobolDivisionElement {

	enum DebugOnType {
		ALL_PROCEDURES, ALL_REFERENCES, FILE, PROCEDURE
	}

	DebugOnType getDebugOnType();

	Call getOnCall();

	void setOnCall(Call onCall);

	void setType(DebugOnType debugOnType);
}
