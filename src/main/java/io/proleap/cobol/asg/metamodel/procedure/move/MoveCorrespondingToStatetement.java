/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.move;

import java.util.List;

import io.proleap.cobol.CobolParser.MoveCorrespondingToSendingAreaContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface MoveCorrespondingToStatetement extends CobolDivisionElement {

	MoveCorrespondingToSendingArea addMoveCorrespondingToSendingArea(MoveCorrespondingToSendingAreaContext ctx);

	void addReceivingAreaCall(Call receivingAreaCall);

	MoveCorrespondingToSendingArea getMoveToCorrespondingSendingArea();

	List<Call> getReceivingAreaCalls();
}
