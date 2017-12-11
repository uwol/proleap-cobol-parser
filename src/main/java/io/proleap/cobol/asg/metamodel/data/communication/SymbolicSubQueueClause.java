/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface SymbolicSubQueueClause extends CobolDivisionElement {

	enum SymbolicSubQueueClauseType {
		SUB_QUEUE_1, SUB_QUEUE_2, SUB_QUEUE_3
	}

	Call getDataDescCall();

	SymbolicSubQueueClauseType getSymbolicSubQueueClauseType();

	void setDataDescCall(Call dataDescCall);

	void setSymbolicSubQueueClauseType(SymbolicSubQueueClauseType symbolicSubQueueClauseType);

}
