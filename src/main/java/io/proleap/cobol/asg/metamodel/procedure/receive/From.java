/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface From extends CobolDivisionElement {

	enum FromType {
		ANY_THREAD, LAST_THREAD, THREAD
	}

	FromType getFromType();

	Call getThreadCall();

	void setFromType(FromType fromType);

	void setThreadCall(Call threadCall);
}
