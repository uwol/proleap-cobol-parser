/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface SymbolicSubQueueClause extends CobolDivisionElement {

	enum Type {
		SUB_QUEUE_1, SUB_QUEUE_2, SUB_QUEUE_3
	}

	Call getDataDescCall();

	Type getType();

	void setDataDescCall(Call dataDescCall);

	void setType(Type type);

}
